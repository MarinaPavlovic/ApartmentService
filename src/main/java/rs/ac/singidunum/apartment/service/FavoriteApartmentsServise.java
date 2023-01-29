package rs.ac.singidunum.apartment.service;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.apartment.entity.FavoriteApmtsEntity;
import rs.ac.singidunum.apartment.model.Apartment;
import rs.ac.singidunum.apartment.model.FavoriteApmts;
import rs.ac.singidunum.apartment.repository.IFavoriteAmptsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service()
public class FavoriteApartmentsServise implements IFavoriteApartmentsService{

    private IFavoriteAmptsRepository favoriteAmptsRepository;
    private IApartmentService apartmentService;

    public FavoriteApartmentsServise(IFavoriteAmptsRepository favoriteAmptsRepository, IApartmentService apartmentService) {
        this.favoriteAmptsRepository = favoriteAmptsRepository;
        this.apartmentService = apartmentService;
    }



    @Override
    public List<Apartment> GetFavoriteApmts(Integer userId) {
        List<FavoriteApmtsEntity> favoriteApmtsEntities=favoriteAmptsRepository.GetFavoriteApmpts(userId);
        List<Apartment> apartments = new ArrayList<>();
        for (FavoriteApmtsEntity ap: favoriteApmtsEntities){
            Apartment apartment = apartmentService.GetApartmentById(ap.getApartmentId());
            if(apartment!= null) {
                apartments.add(apartment);
            }
        }
        if(apartments == null){
            return null;
        }
        return apartments;
    }

    @Override
    public void AddFavoriteApartment(FavoriteApmts apartment) {
        List<FavoriteApmtsEntity> favoriteApmtsEntities=favoriteAmptsRepository.GetFavoriteApmpts(apartment.getUserId());
        int counter=0;
        for(FavoriteApmtsEntity ap: favoriteApmtsEntities){
            if(apartment.getApartmentId()==ap.getApartmentId()){
                counter++;
            }
        }
        if(counter==0) {
            FavoriteApmtsEntity apmtsEntity = new FavoriteApmtsEntity(apartment.getId(), apartment.getApartmentId(), apartment.getUserId());
            favoriteAmptsRepository.save(apmtsEntity);
        }

    }

    @Override
    public void DeleteFavoriteApmts(FavoriteApmts favoriteApmts) {

        Integer apmtId=favoriteAmptsRepository.GetFavoriteApmtId(favoriteApmts.getUserId(), favoriteApmts.getApartmentId());
        favoriteAmptsRepository.deleteById(apmtId);

    }
}
