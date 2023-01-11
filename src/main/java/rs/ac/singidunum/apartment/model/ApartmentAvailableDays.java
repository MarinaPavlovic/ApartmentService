package rs.ac.singidunum.apartment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentAvailableDays {
    private Integer id;
    @NotNull
    private Integer apartmentId;
    @NotNull
    private Date startingDay;
    @NotNull
    private Date endDay;
}
