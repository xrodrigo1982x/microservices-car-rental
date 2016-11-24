package car.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

@Data
public class Car {

    @NotEmpty
    private String model;
    @NotEmpty
    private String brand;

    private BigDecimal factor;

}
