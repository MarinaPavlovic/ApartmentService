package rs.ac.singidunum.apartment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.apartment.model.Apartment;
import rs.ac.singidunum.apartment.model.FilterModel;
import rs.ac.singidunum.apartment.service.IFilterService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("filter")
public class FilterController {
    private IFilterService filterService;

    public FilterController(IFilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping("get/content")
    @CrossOrigin("*")
    public FilterModel getFilterContent(){
        return filterService.getFilterContent();
    }

    @PostMapping("all")
    @CrossOrigin("*")
    public ResponseEntity<?> filteredApartments (@RequestBody @Valid FilterModel filterModel, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<String>(result.getAllErrors().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<Apartment>>(filterService.filterResult(filterModel),HttpStatus.OK);
    }
}
