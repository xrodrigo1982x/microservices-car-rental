package car.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class OfferPrice {

    @Id
    private String id;
    private BigDecimal price;

}
