package rs.ac.singidunum.apartment.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "favorite_apartments")
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteApmtsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name = "apartment_id")
    private Integer apartmentId;
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
}
