package car.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Location {

    @NotNull
    private Double[] pickUp;
    @NotNull
    private Double[] dropOff;

}
