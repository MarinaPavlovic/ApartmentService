package rs.ac.singidunum.apartment.service;

import rs.ac.singidunum.apartment.model.ApartmentAvailableDays;

public interface IApartmentsAvDayService {
    ApartmentAvailableDays GetById (Integer id);
    ApartmentAvailableDays Create (ApartmentAvailableDays apartmentAD);
    ApartmentAvailableDays Edit (ApartmentAvailableDays apartmentAD);
    void Delete (Integer id);




}
