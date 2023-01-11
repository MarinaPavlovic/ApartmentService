package rs.ac.singidunum.apartment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentImages {
    private Integer id;
    @NotNull
    private Integer apartmentId;
    @URL
    @NotNull
    private String imageURL;
}
