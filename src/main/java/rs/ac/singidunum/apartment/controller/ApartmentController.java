package rs.ac.singidunum.apartment.controller;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.apartment.model.Apartment;
import rs.ac.singidunum.apartment.model.CreateApartmentModel;
import rs.ac.singidunum.apartment.model.FavoriteApmts;
import rs.ac.singidunum.apartment.model.RequestDestinationType;
import rs.ac.singidunum.apartment.service.IApartmentService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("apartment")
public class ApartmentController {
    private IApartmentService apartmentService;

    public ApartmentController(IApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("get/by/id")
    @CrossOrigin("*")
    public Apartment GetApartmentById (Integer id){
        return apartmentService.GetApartmentById(id);
    }

    @PostMapping("create")
    @CrossOrigin("*")
    public ResponseEntity<?> CreateApartment (@RequestBody @Valid CreateApartmentModel apartment, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Apartment>(apartmentService.CreateApartment(apartment), HttpStatus.OK) ;
    }

    @PostMapping("edit")
    @CrossOrigin("*")
    public ResponseEntity<?> EditApartment (@RequestBody @Valid CreateApartmentModel apartment, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Apartment>(apartmentService.EditApartment(apartment), HttpStatus.OK) ;
    }

    @GetMapping("delete")
    @CrossOrigin("*")
    public void DeleteApartment (Integer id){
        apartmentService.DeleteApartment(id);
    }

    @GetMapping("get/all/filter")
    @CrossOrigin("*")
    public List<Apartment> GetAllApartmentsFilter (@RequestParam("from") Double from, @RequestParam("to") Double to){
        return apartmentService.ApartmentPriceLimit(from, to);
    }
    @GetMapping("get/host/apartments/{id}")
    @CrossOrigin("*")
    public List<Apartment> GetHostApartments (@PathVariable Integer id ){

        return apartmentService.GetUserApartments(id);
    }

    @GetMapping("get/all")
    @CrossOrigin("*")
    public List<Apartment> GetAllApartments (){
        return apartmentService.AllApartments();
    }

    @PostMapping("get/type")
    @CrossOrigin("*")
    public List<Apartment> GetApartmentDestinationType (@RequestBody RequestDestinationType type){

        return apartmentService.ApartmentDestinationFilter(type);
    }



}
