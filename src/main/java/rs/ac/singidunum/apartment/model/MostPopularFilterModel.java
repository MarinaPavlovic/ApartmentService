package rs.ac.singidunum.apartment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostPopularFilterModel {
    private Integer apartmentId;
    private int counter;
}
