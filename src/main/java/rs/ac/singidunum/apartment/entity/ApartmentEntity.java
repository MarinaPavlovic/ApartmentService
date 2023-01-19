package rs.ac.singidunum.apartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.singidunum.apartment.model.ApartmentDestinationType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name="apartmants")
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    @NotNull
    private Integer userId;
    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;
    @Column(name = "description")
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    @Column(name = "country")
    private String country;
    @NotNull
    @NotBlank
    @Column(name = "city")
    private String city;
    @Column(name = "adres")
    @NotNull
    @NotBlank
    private String adres;
    @Column(name = "price_per_night")
    @NotNull
    private Double pricePerNight;
    @Column(name= "destinationType")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ApartmentDestinationType destinationType;


}
