package car.model;

import lombok.Data;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.math.BigDecimal;

@Table
@Data
public class Offer {

    @PrimaryKey
    private String id;
    private BigDecimal price;

}
