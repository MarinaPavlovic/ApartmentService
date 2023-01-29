package rs.ac.singidunum.apartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.singidunum.apartment.entity.ApartmentImagesEntity;

import java.util.List;

public interface IApartmentImagesRepository extends JpaRepository<ApartmentImagesEntity,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM apartment_images WHERE apartment_id= :idOfApartment ")
    List<ApartmentImagesEntity> ImagesByApartmentId (@Param("idOfApartment") Integer idOfApartment);

    @Modifying
    @Query(nativeQuery = true , value = "DELETE FROM apartment_images WHERE apartment_id= :idOfApartment")
    void DeleteApartmentImages(@Param("idOfApartment") Integer idOfApartment);

}
