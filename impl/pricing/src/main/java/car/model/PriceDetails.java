package car.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PriceDetails {

    private Car car;
    private Double[] origin;
    private Double[] destination;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date pickUp;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private Date dropOff;
    private List<Offer> offers;
    private Coupom coupom;

    @JsonProperty
    public BigDecimal getTotalPrice(){
        return null;
    }

}
