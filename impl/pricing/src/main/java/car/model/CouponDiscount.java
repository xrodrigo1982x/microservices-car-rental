package car.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CouponDiscount {

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

    @Id
    private String id;
    @NotNull
    private DiscountType type;
    @NotNull
    private BigDecimal discount;

    public BigDecimal apply(BigDecimal price) {
        return type.apply(price, discount);
    }

}
