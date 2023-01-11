package rs.ac.singidunum.apartment.service;

import rs.ac.singidunum.apartment.model.Apartment;
import rs.ac.singidunum.apartment.model.CreateApartmentModel;
import rs.ac.singidunum.apartment.model.FavoriteApmts;
import rs.ac.singidunum.apartment.model.RequestDestinationType;

import java.util.ArrayList;
import java.util.List;

public interface IApartmentService {
    Apartment GetApartmentById (Integer id);
    Apartment CreateApartment (CreateApartmentModel apartment);
    Apartment EditApartment (CreateApartmentModel apartment);
    void DeleteApartment (Integer id);
    List<Apartment> AllApartments ();
    List<Apartment> ApartmentPriceLimit(Double from, Double to);
    List<Apartment> ApartmentDestinationFilter (RequestDestinationType type);
    List<Apartment> GetUserApartments(Integer id);





}