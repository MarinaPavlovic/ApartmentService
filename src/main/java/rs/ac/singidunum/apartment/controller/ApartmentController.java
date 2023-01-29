package rs.ac.singidunum.apartment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.apartment.model.*;
import rs.ac.singidunum.apartment.service.IApartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("apartment")
public class ApartmentController {
    private IApartmentService apartmentService;

    public ApartmentController(IApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("{id}")
    @CrossOrigin("*")
    public Apartment GetApartmentById (@PathVariable Integer id){
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

    @DeleteMapping("{id}")
    @CrossOrigin("*")
    public void DeleteApartment (@PathVariable("id") Integer id){
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
    @PostMapping("reservations")
    @CrossOrigin("*")
    public List<CreateApartmentModel> GetApartmentReservations(@RequestBody RequestUserReservations apartmentsId){
        return apartmentService.ApartmentReservations(apartmentsId);
    }

    @CrossOrigin("*")
    @DeleteMapping("delete/all/{userId}")
    public void DeleteAllUsersApartments(@PathVariable("userId") Integer userId){
        apartmentService.deleteApartmentsWhenUserIsDelete(userId);
    }



}
