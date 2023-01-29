package rs.ac.singidunum.apartment.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.ac.singidunum.apartment.model.*;
import rs.ac.singidunum.apartment.repository.IApartmentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterService implements IFilterService {

    private RestTemplate restTemplate;
    private IApartmentService apartmentService;
    public FilterService(IApartmentRepository apartmentRepository, RestTemplate restTemplate, IApartmentService apartmentService) {
        this.restTemplate = restTemplate;
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
        ApartmentDestinationType apartmentDestinationType= ApartmentDestinationType.valueOf("ALL");
        FilterModel filterModel = new FilterModel(countries,cities,0,apartmentDestinationType);
        return filterModel;
    }

    public List<Apartment> filteredApartByDest(FilterModel filterModel, List<Apartment> apartments) {
        List<Apartment> filteredApartments=new ArrayList<Apartment>();
        if(!filterModel.getCountries().isEmpty()){
            for (Apartment ap: apartments){
                for(String country: filterModel.getCountries()) {
                    if (ap.getCountry().equals(country)){
                        filteredApartments.add(ap);

                    }
                }
            }
        }

        if(!filterModel.getCities().isEmpty()){
            for(Apartment ap: apartments ){
                for(String city: filterModel.getCities()){
                    if(ap.getCity().equals(city)){
                        filteredApartments.add(ap);
                    }
                }
            }
        }

       if(filterModel.getCities().isEmpty() && filterModel.getCountries().isEmpty()){
            return apartments;
        }

       filteredApartments=filteredApartments.stream().distinct().collect(Collectors.toList());

        return filteredApartments;
    }

    public List<Apartment> sortByPrice(Integer sortMethod, List<Apartment> apartments) {
        if(sortMethod==1){
            apartments.sort(Comparator.comparing(Apartment::getPricePerNight));
            return apartments;
        }else if(sortMethod==2){
            apartments.sort(Comparator.comparing(Apartment::getPricePerNight));
            Collections.reverse(apartments);
            return apartments;
        }

        return apartments;
    }

    public Integer[] QuestForUserMS(){
        String USER_URL_MS="http://localhost:8080/reservations/apartments/id";
        ResponseEntity<Integer[]> response = restTemplate.getForEntity(USER_URL_MS,Integer[].class);
        Integer[] apartmentId= response.getBody();
        return  apartmentId;
    }

    public List<Apartment> mostPopularFilter(List<Apartment> apartments) {
        Integer[] apartmentsId= QuestForUserMS();
        List<MostPopularFilterModel> countNumberOfRes=new ArrayList<MostPopularFilterModel>();
        for(Apartment ap:apartments){
            MostPopularFilterModel filterModel=new MostPopularFilterModel(ap.getId(),0);
            countNumberOfRes.add(filterModel);
        }

        for(Integer id:apartmentsId){
            for(MostPopularFilterModel ap: countNumberOfRes){
                if(id==ap.getApartmentId()){
                    Integer namberOfRes=ap.getCounter();
                    ap.setCounter(namberOfRes+1);
                }
            }
        }

        countNumberOfRes.sort(Comparator.comparing(MostPopularFilterModel::getCounter));
        Collections.reverse(countNumberOfRes);
        List<Apartment> sortedApmts = new ArrayList<Apartment>();
        for(MostPopularFilterModel fm:countNumberOfRes){
            Apartment ap = apartmentService.GetApartmentById(fm.getApartmentId());
            sortedApmts.add(ap);
        }
        return sortedApmts;
    }

    @Override
    public List<Apartment> filterResult(FilterModel filterModel) {
        List<Apartment> filteredList= new ArrayList<Apartment>();
        if(filterModel.getDestinationType().name()!="ALL"){
            RequestDestinationType requestDestinationType=new RequestDestinationType(filterModel.getDestinationType());
            filteredList=apartmentService.ApartmentDestinationFilter(requestDestinationType);
        }else{
            filteredList=apartmentService.AllApartments();
        }

        filteredList= filteredApartByDest(filterModel,filteredList);
        if(filterModel.getSort()==1 || filterModel.getSort()==2) {
            filteredList = sortByPrice(filterModel.getSort(), filteredList);
        }
        if(filterModel.getSort()==3){
            filteredList=mostPopularFilter(filteredList);
        }
        return filteredList;
    }
}

