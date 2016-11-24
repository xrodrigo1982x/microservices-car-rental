package car.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.context.SpatialContextFactory;
import com.spatial4j.core.distance.GeodesicSphereDistCalc;
import com.spatial4j.core.shape.impl.PointImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class PriceDetails {

    private static final SpatialContext sc = new SpatialContextFactory().newSpatialContext();

    @Getter(onMethod = @__( @JsonIgnore ))
    private Car car;
    @Getter(onMethod = @__( @JsonIgnore ))
    private Double[] origin;
    @Getter(onMethod = @__( @JsonIgnore ))
    private Double[] destination;
    @Getter(onMethod = @__( @JsonIgnore ))
    private Date pickUp;
    @Getter(onMethod = @__( @JsonIgnore ))
    private Date dropOff;
    @Getter
    private List<Offer> offers;
    private Coupom coupom;

    @JsonProperty
    public BigDecimal getDailyPrice(){
        return car.getDailyPrice();
    }

    @JsonProperty
    public BigDecimal distanceFactor() {
        return new BigDecimal(new GeodesicSphereDistCalc.Haversine().distance(
                new PointImpl(origin[0], origin[1], sc),
                new PointImpl(destination[0], destination[1], sc)
        ));
    }

    @JsonProperty
    public Long getDays(){
        return Duration.between(pickUp.toInstant(), dropOff.toInstant()).toDays() + 1;
    }

    @JsonProperty
    public BigDecimal getTotalPrice(){
        return getDailyPrice().multiply(new BigDecimal(getDays())).add(getTotalOffers());
    }

    @JsonProperty
    public BigDecimal getFinalPrice() {
        return coupom.apply(getTotalPrice());
    }

    @JsonProperty
    public BigDecimal getTotalOffers(){
        return new BigDecimal(offers.stream().mapToDouble(offer -> offer.getPrice().doubleValue()).sum());
    }

}
