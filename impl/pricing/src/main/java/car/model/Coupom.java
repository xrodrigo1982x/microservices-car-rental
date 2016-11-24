package car.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table
@Data
public class Coupom {

    public enum DiscountType {
        PERCENTAGE {
            public final BigDecimal HUNDRED = new BigDecimal(100);

            @Override
            public BigDecimal apply(BigDecimal price, BigDecimal discount) {
                return HUNDRED.subtract(discount).divide(HUNDRED).multiply(price);
            }
        }, VALUE {
            @Override
            public BigDecimal apply(BigDecimal price, BigDecimal discount) {
                return price.subtract(discount);
            }
        };

        public abstract BigDecimal apply(BigDecimal price, BigDecimal discount);

    }

    @PrimaryKey
    private String id;
    @NotEmpty
    private String type;
    @NotNull
    private BigDecimal discount;

    public BigDecimal apply(BigDecimal price) {
        return DiscountType.valueOf(type).apply(price, discount);
    }

}
