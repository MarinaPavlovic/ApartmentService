package rs.ac.singidunum.apartment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteApmts {

    private Integer id;
    @NotNull
    private Integer apartmentId;
    @NotNull
    private Integer userId;
}
