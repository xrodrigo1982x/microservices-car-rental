package car.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class Pricing {

    @NotNull
    private String car;
    @NotNull
    private Location location;
    @NotNull
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date pickUpDate;
    @NotNull
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date dropOffDate;
    private List<String> offers;
    private String coupon;

}
