package rs.ac.singidunum.apartment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.apartment.model.ApartmentAvailableDays;
import rs.ac.singidunum.apartment.service.IApartmentsAvDayService;

import javax.validation.Valid;

@RestController
@RequestMapping("apartmentAvDays")
public class ApartmentAvDaysController {
    private IApartmentsAvDayService apartmentsAvDayService;

    public ApartmentAvDaysController(IApartmentsAvDayService apartmentsAvDayService) {
        this.apartmentsAvDayService = apartmentsAvDayService;
    }
    @GetMapping("get/by/id")
    @CrossOrigin("*")
    public ApartmentAvailableDays GetById (Integer id){
        return apartmentsAvDayService.GetById(id);
    }
    @PostMapping("create")
    @CrossOrigin("*")
    public ResponseEntity<?> Create (@RequestBody @Valid ApartmentAvailableDays apartmentAD, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ApartmentAvailableDays>(apartmentsAvDayService.Create(apartmentAD),HttpStatus.OK);
    }
    @PostMapping("edit")
    @CrossOrigin("*")
    public ResponseEntity<?> Edit (@RequestBody @Valid ApartmentAvailableDays apartmentAd,BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ApartmentAvailableDays>(apartmentsAvDayService.Edit(apartmentAd),HttpStatus.OK);
    }
    @GetMapping("delete")
    @CrossOrigin("*")
    public void Delete(Integer id){
        apartmentsAvDayService.Delete(id);
    }

}
