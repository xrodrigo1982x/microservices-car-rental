package car.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class PriceDetails {

    private BigDecimal dailyPrice;
    private BigDecimal distanceFactor;
    private List<Offer> offers;
    private Coupom coupom;
    private Long days;
    private BigDecimal totalPrice;
    private BigDecimal finalPrice;
    private BigDecimal totalOffers;

    public PriceDetails calculate(){
        totalOffers = new BigDecimal(offers == null ? 0 : offers.stream().mapToDouble(offer -> offer.getPrice().doubleValue()).sum());
        totalPrice = getDailyPrice().multiply(new BigDecimal(getDays())).add(getTotalOffers());
        finalPrice = coupom != null ? coupom.apply(getTotalPrice()) : getTotalPrice();
        return this;
    }

}
