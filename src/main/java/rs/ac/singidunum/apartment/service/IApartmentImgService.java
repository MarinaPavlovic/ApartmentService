package rs.ac.singidunum.apartment.service;

import rs.ac.singidunum.apartment.model.ApartmentImages;

import java.util.ArrayList;
import java.util.List;

public interface IApartmentImgService {
    ApartmentImages GetById (Integer id);
    ApartmentImages Create (String apartmentImg , Integer apartmentId);
    void Delete (Integer id);
    List<ApartmentImages> GetImagesByApartmentID (Integer apartmentId);
    void DeleteApartmentImg (Integer apartmentId);


}
