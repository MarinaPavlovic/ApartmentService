package rs.ac.singidunum.apartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "apartment_images")
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name = "apartment_id")
    private Integer apartmentId;
    @Column(name = "image_url")
    @URL
    @NotNull
    private String imageURL;
}
