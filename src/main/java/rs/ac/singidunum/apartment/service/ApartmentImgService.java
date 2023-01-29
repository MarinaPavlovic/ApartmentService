package rs.ac.singidunum.apartment.service;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.apartment.model.ApartmentImages;
import rs.ac.singidunum.apartment.entity.ApartmentImagesEntity;
import rs.ac.singidunum.apartment.repository.IApartmentImagesRepository;

import java.util.List;
import java.util.Optional;

@Service()
public class ApartmentImgService implements IApartmentImgService{
    private IApartmentImagesRepository apartmentImagesRepository;

    public ApartmentImgService(IApartmentImagesRepository apartmentImagesRepository) {
        this.apartmentImagesRepository = apartmentImagesRepository;
    }

    @Override
    public ApartmentImages GetById(Integer id) {
        Optional<ApartmentImagesEntity> apIE= apartmentImagesRepository.findById(id);
        ApartmentImages apartmentImages=new ApartmentImages(apIE.get().getId(),apIE.get().getApartmentId(),apIE.get().getImageURL());
        return apartmentImages;

    }


    @Override
    public ApartmentImages Create(String apartmentImg , Integer apartmentId) {
        ApartmentImagesEntity apIE=new ApartmentImagesEntity(null,apartmentId,apartmentImg);
        apIE=apartmentImagesRepository.save(apIE);
        ApartmentImages apImg=new ApartmentImages(apIE.getId(),apIE.getApartmentId(),apIE.getImageURL());
        return apImg;
    }

    @Override
    public void Delete(Integer id) {
        apartmentImagesRepository.deleteById(id);

    }


    @Override
    public List<ApartmentImages> GetImagesByApartmentID(Integer apartmentId) {
        List<ApartmentImagesEntity> imagesList=apartmentImagesRepository.ImagesByApartmentId(apartmentId);
        return imagesList.stream().map(ApartmentImagesEntity -> new ApartmentImages(ApartmentImagesEntity.getId(),ApartmentImagesEntity.getApartmentId(),ApartmentImagesEntity.getImageURL())).toList();
    }

    @Override
    public void DeleteApartmentImg(Integer apartmentId) {
        apartmentImagesRepository.DeleteApartmentImages(apartmentId);
    }
}
