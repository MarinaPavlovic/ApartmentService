package rs.ac.singidunum.apartment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilterModel {
    private List<String> countries;
    private List<String> cities;
    private Integer sort;
    private ApartmentDestinationType destinationType;


}
