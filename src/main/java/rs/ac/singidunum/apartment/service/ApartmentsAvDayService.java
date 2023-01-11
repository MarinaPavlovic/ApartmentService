package rs.ac.singidunum.apartment.service;

import org.springframework.stereotype.Service;
import rs.ac.singidunum.apartment.model.ApartmentAvailableDays;
import rs.ac.singidunum.apartment.entity.ApartmentAvailableDaysEntity;
import rs.ac.singidunum.apartment.repository.IApartmentAvDaysRepository;

import java.util.Optional;

@Service()
public class ApartmentsAvDayService implements IApartmentsAvDayService{
    private IApartmentAvDaysRepository apartmentAvDaysRepository;

    public ApartmentsAvDayService(IApartmentAvDaysRepository apartmentAvDaysRepository) {
        this.apartmentAvDaysRepository = apartmentAvDaysRepository;
    }

    @Override
    public ApartmentAvailableDays GetById(Integer id) {
        Optional<ApartmentAvailableDaysEntity> apAvDEntity=apartmentAvDaysRepository.findById(id);
        if(apAvDEntity!=null){
            ApartmentAvailableDays apAvD = new ApartmentAvailableDays(apAvDEntity.get().getId(),apAvDEntity.get().getApartmentId(),apAvDEntity.get().getStartingDay(),apAvDEntity.get().getEndDay());
            return apAvD;
        }
        return null;
    }

    @Override
    public ApartmentAvailableDays Create(ApartmentAvailableDays apartmentAD) {
        ApartmentAvailableDaysEntity apAvDEntity= new ApartmentAvailableDaysEntity(apartmentAD.getId(),apartmentAD.getApartmentId(),apartmentAD.getStartingDay(),apartmentAD.getEndDay());
        apAvDEntity =apartmentAvDaysRepository.save(apAvDEntity);
        apartmentAD.setId(apAvDEntity.getId());
        return apartmentAD;
    }

    @Override
    public ApartmentAvailableDays Edit(ApartmentAvailableDays apartmentAD) {
        ApartmentAvailableDaysEntity apAvDEntity= new ApartmentAvailableDaysEntity(apartmentAD.getId(),apartmentAD.getApartmentId(),apartmentAD.getStartingDay(),apartmentAD.getEndDay());
        apartmentAvDaysRepository.save(apAvDEntity);
        return apartmentAD;
    }

    @Override
    public void Delete(Integer id) {
        apartmentAvDaysRepository.deleteById(id);

    }
}
