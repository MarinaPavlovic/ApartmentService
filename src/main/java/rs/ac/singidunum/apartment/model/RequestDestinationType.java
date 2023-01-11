package rs.ac.singidunum.apartment.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
public class RequestDestinationType {

    private ApartmentDestinationType type;



    public ApartmentDestinationType getType() {
        return type;
    }

    public void setType(ApartmentDestinationType type) {
        this.type = type;
    }
}

