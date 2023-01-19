package rs.ac.singidunum.apartment.service;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.apartment.model.Apartment;
import rs.ac.singidunum.apartment.model.FilterModel;
import rs.ac.singidunum.apartment.repository.IApartmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterService implements IFilterService {
    private IApartmentService apartmentService;
    public FilterService(IApartmentRepository apartmentRepository, IApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public FilterModel getFilterContent() {
        List<Apartment> apartments=apartmentService.AllApartments();
        List<String> countries = new ArrayList<String>();
        List<String> cities = new ArrayList<String>();
        for(Apartment ap: apartments){
            countries.add(ap.getCountry());
            cities.add(ap.getCity());
        }
        countries=countries.stream().distinct().collect(Collectors.toList());
        cities=cities.stream().distinct().collect(Collectors.toList());
        FilterModel filterModel = new FilterModel(countries,cities);
        return filterModel;
    }
}
