package rs.ac.singidunum.apartment.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.apartment.model.FilterModel;
import rs.ac.singidunum.apartment.service.IFilterService;

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
}
