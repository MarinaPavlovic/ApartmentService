package rs.ac.singidunum.apartment.service;

import rs.ac.singidunum.apartment.model.Apartment;
import rs.ac.singidunum.apartment.model.FilterModel;

import java.util.List;

public interface IFilterService {
    FilterModel getFilterContent ();

    List<Apartment> filterResult (FilterModel filterModel);
}
