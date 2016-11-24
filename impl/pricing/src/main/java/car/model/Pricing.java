package car.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class Pricing {

    @NotNull
    private Car car;
    @NotNull
    private Location location;
    @NotNull
    private Date pickUpDate;
    @NotNull
    private Date dropOffDate;
    private List<String> offers;
    private String coupon;

}
