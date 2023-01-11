package rs.ac.singidunum.apartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.apartment.entity.ApartmentAvailableDaysEntity;

public interface IApartmentAvDaysRepository extends JpaRepository<ApartmentAvailableDaysEntity, Integer> {
}
