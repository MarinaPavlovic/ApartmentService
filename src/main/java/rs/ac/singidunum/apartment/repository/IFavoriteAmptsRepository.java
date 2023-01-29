package rs.ac.singidunum.apartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.singidunum.apartment.entity.FavoriteApmtsEntity;

import java.util.List;

public interface IFavoriteAmptsRepository extends JpaRepository<FavoriteApmtsEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM favorite_apartments WHERE user_id = :userId")
    List<FavoriteApmtsEntity> GetFavoriteApmpts(@Param("userId") Integer userId);

    @Query(nativeQuery = true, value = "SELECT id FROM favorite_apartments WHERE(user_id= :userId AND apartment_id= :apartmentId)")
    Integer GetFavoriteApmtId (@Param("userId") Integer userId, @Param("apartmentId") Integer apartmentId);

}
