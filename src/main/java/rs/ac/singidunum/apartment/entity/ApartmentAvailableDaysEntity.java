package rs.ac.singidunum.apartment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "apartmant_available_days")
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentAvailableDaysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "apartment_id")
    @NotNull
    private Integer apartmentId;
    @Column(name = "starting_day")
    @NotNull
    private Date startingDay;
    @Column(name = "end_day")
    @NotNull
    private Date endDay;
}
