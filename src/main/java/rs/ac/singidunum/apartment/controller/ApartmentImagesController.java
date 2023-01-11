package rs.ac.singidunum.apartment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.apartment.model.ApartmentImages;
import rs.ac.singidunum.apartment.service.IApartmentImgService;

import javax.validation.Valid;

@RestController
@RequestMapping("apartmentImages")
public class ApartmentImagesController {
    private IApartmentImgService apartmentImgService;

    public ApartmentImagesController(IApartmentImgService apartmentImgService) {
        this.apartmentImgService = apartmentImgService;
    }
    @GetMapping("get/by/id")
    @CrossOrigin("*")
    public ApartmentImages GetById (Integer id){
        return apartmentImgService.GetById(id);

    }
    @PostMapping("create")
    @CrossOrigin("*")
    public ResponseEntity<?> Create (@RequestBody@Valid String apartmentImg , Integer apartmentId, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ApartmentImages>(apartmentImgService.Create(apartmentImg,apartmentId),HttpStatus.OK);
    }
    @GetMapping("delete/{id}")
    @CrossOrigin("*")
    public void Delete (@PathVariable("id") Integer id){
        apartmentImgService.Delete(id);
    }
}
