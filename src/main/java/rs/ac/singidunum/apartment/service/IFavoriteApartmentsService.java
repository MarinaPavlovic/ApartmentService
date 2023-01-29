package rs.ac.singidunum.apartment.service;

import rs.ac.singidunum.apartment.entity.FavoriteApmtsEntity;
import rs.ac.singidunum.apartment.model.Apartment;
import rs.ac.singidunum.apartment.model.FavoriteApmts;

import java.util.List;
import java.util.Optional;

public interface IFavoriteApartmentsService {
    List<Apartment> GetFavoriteApmts(Integer userId);
    void AddFavoriteApartment (FavoriteApmts apartment);
    void DeleteFavoriteApmts (FavoriteApmts favoriteApmts);
}
