package rs.ac.singidunum.apartment.service;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.apartment.entity.ApartmentEntity;
import rs.ac.singidunum.apartment.entity.FavoriteApmtsEntity;
import rs.ac.singidunum.apartment.model.*;
import rs.ac.singidunum.apartment.repository.IApartmentRepository;
import rs.ac.singidunum.apartment.repository.IFavoriteAmptsRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service()
public class ApartmentService implements IApartmentService {
    private IApartmentRepository apartmentRepository;
    private IApartmentImgService apartmentImgService;

    public ApartmentService(IApartmentRepository apartmentRepository, IApartmentImgService apartmentImgService, IFavoriteAmptsRepository favoriteAmptsRepository) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentImgService = apartmentImgService;
    }

    @Override
    public Apartment GetApartmentById(Integer id) {
        Optional<ApartmentEntity> apartmentEntity = apartmentRepository.findById(id);
        if (apartmentEntity == null) {
            return null;
        }
        List<ApartmentImages> apartmentImages = apartmentImgService.GetImagesByApartmentID(id);
        Apartment apartment = new Apartment(apartmentEntity.get().getId(), apartmentEntity.get().getUserId(), apartmentEntity.get().getName(), apartmentEntity.get().getDescription(),apartmentEntity.get().getCountry(),apartmentEntity.get().getCity() ,apartmentEntity.get().getAdres(), apartmentEntity.get().getPricePerNight(), apartmentEntity.get().getDestinationType(), apartmentImages);
        return apartment;
    }

    public List<Apartment> getApartmentImg (List<ApartmentEntity> apartmentEntities){
        List<Apartment> apartments = new ArrayList<Apartment>();
        for (ApartmentEntity ap : apartmentEntities) {
            List<ApartmentImages> apartmentImages = apartmentImgService.GetImagesByApartmentID(ap.getId());
            Apartment apartment = new Apartment(ap.getId(), ap.getUserId(), ap.getName(), ap.getDescription(),ap.getCountry(),ap.getCity(), ap.getAdres(), ap.getPricePerNight(), ap.getDestinationType(), apartmentImages);
            apartments.add(apartment);
        }
        return apartments;
    }

    @Override
    @Transactional
    public Apartment CreateApartment(CreateApartmentModel apartment) {
        ApartmentDestinationType destinationType= ApartmentDestinationType.valueOf(apartment.getDestinationType());
        ApartmentEntity apartmentEntity = new ApartmentEntity(apartment.getId(), apartment.getUserId(), apartment.getName(), apartment.getDescription(), apartment.getCountry(), apartment.getCity(), apartment.getAdres(), apartment.getPricePerNight(), destinationType);
        apartmentEntity = apartmentRepository.save(apartmentEntity);
        apartment.setId(apartmentEntity.getId());

        for(String img: apartment.getImages() ){
            apartmentImgService.Create(img,apartment.getId());
        }
        List<ApartmentImages> images = apartmentImgService.GetImagesByApartmentID(apartment.getId());

        Apartment newApartment= new Apartment(apartmentEntity.getId(),apartmentEntity.getUserId(), apartmentEntity.getName(),apartmentEntity.getDescription(), apartment.getCountry(), apartment.getCity(), apartmentEntity.getAdres(),apartmentEntity.getPricePerNight(),apartmentEntity.getDestinationType(), images );

        return newApartment;
    }

    @Override
    public Apartment EditApartment(CreateApartmentModel apartment) {
        ApartmentDestinationType destinationType= ApartmentDestinationType.valueOf(apartment.getDestinationType());
        ApartmentEntity apartmentEntity = new ApartmentEntity(apartment.getId(), apartment.getUserId(), apartment.getName(), apartment.getDescription(), apartment.getCountry(), apartment.getCity(), apartment.getAdres(), apartment.getPricePerNight(), destinationType);
        apartmentEntity = apartmentRepository.save(apartmentEntity);
        for(String img: apartment.getImages() ){
            apartmentImgService.Create(img,apartment.getId());
        }
        List<ApartmentImages> apartmentImages = apartmentImgService.GetImagesByApartmentID(apartment.getId());
        Apartment changedApartment = new Apartment(apartmentEntity.getId(), apartmentEntity.getUserId(), apartmentEntity.getName(), apartmentEntity.getDescription(), apartmentEntity.getCountry(), apartment.getCity(), apartmentEntity.getAdres(), apartmentEntity.getPricePerNight(), apartmentEntity.getDestinationType(), apartmentImages);
        return changedApartment;
    }

    @Override
    public void DeleteApartment(Integer id) {
        apartmentRepository.deleteById(id);
    }

    @Override
    public List<Apartment> AllApartments() {
        List<ApartmentEntity> allApartments = apartmentRepository.findAll();
        List<Apartment> apartments =getApartmentImg(allApartments);
        return apartments;

    }

    @Override
    public List<Apartment> ApartmentPriceLimit(Double from, Double to) {
        if (from != null && to != null) {
            List<ApartmentEntity> apartmentEntities = apartmentRepository.ApartmentFilterPrice(from, to);
            List<Apartment> apartments= getApartmentImg(apartmentEntities);
            return apartments;
        }
        return null;
    }

    @Override
    public List<Apartment> ApartmentDestinationFilter(RequestDestinationType type) {
        ApartmentDestinationType destinationType=type.getType();
        String typeOfDestination="";

        switch (destinationType){
            case BEACH:
                typeOfDestination="BEACH";
                break;
            case CITIES:
                typeOfDestination="CITIES";
                break;
            case SKIING:
                typeOfDestination="SKIING";
                break;
        }

        List<ApartmentEntity> filteredApartments = apartmentRepository.ApartmentTypeFilter(typeOfDestination);
        List<Apartment> apartments= getApartmentImg(filteredApartments);
        return apartments;
    }

    @Override
    public List<Apartment> GetUserApartments(Integer id){
        List<ApartmentEntity> apartmentEntities = apartmentRepository.HostApartments(id);
        List<Apartment> apartments = getApartmentImg(apartmentEntities);
        return apartments;
    }

    @Override
    public List<CreateApartmentModel> ApartmentReservations(RequestUserReservations apartmentsId) {
        List<CreateApartmentModel> apartmants = new ArrayList<CreateApartmentModel>();
        for(Integer i :apartmentsId.getId()){
            Apartment apartment=GetApartmentById(i);
            String destType= apartment.getDestinationType().name();
            List<String> url=new ArrayList<String>();
            for(ApartmentImages apI:apartment.getImages()){
                url.add(apI.getImageURL());
            }
            CreateApartmentModel apartmentModel=new CreateApartmentModel(apartment.getId(),apartment.getUserId(),apartment.getName(),apartment.getDescription(), apartment.getCountry(), apartment.getCity(), apartment.getAdres(),apartment.getPricePerNight(),destType,url);
            apartmants.add(apartmentModel);
        }


        return apartmants;
    }


}
