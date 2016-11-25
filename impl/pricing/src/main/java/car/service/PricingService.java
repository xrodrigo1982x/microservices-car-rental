package car.service;

import car.model.*;
import car.repository.CarRepository;
import car.repository.CoupomRepository;
import car.repository.OfferRepository;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.context.SpatialContextFactory;
import com.spatial4j.core.distance.GeodesicSphereDistCalc;
import com.spatial4j.core.shape.impl.PointImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PricingService {

    private static final SpatialContext sc = new SpatialContextFactory().newSpatialContext();

    private CarRepository carRepository;
    private CoupomRepository coupomRepository;
    private OfferRepository offerRepository;

    public PriceDetails pricing(Pricing pricing) {
        Car car = carRepository.findOne(pricing.getCar());
        Coupom coupom = pricing.getCoupon() != null ? coupomRepository.findOne(pricing.getCoupon()) : null;
        List<Offer> offers = pricing.getOffers() != null && !pricing.getOffers().isEmpty() ? (List<Offer>) offerRepository.findAll(pricing.getOffers()) : null;
        BigDecimal distanceFactor = new BigDecimal(new GeodesicSphereDistCalc.Haversine().distance(
                new PointImpl(pricing.getLocation().getPickUp()[0], pricing.getLocation().getPickUp()[1], sc),
                new PointImpl(pricing.getLocation().getDropOff()[0], pricing.getLocation().getDropOff()[1], sc)
        ));
        return PriceDetails.builder()
                .distanceFactor(distanceFactor)
                .dailyPrice(car.getDailyPrice())
                .offers(offers)
                .days(Duration.between(pricing.getPickUpDate().toInstant(), pricing.getDropOffDate().toInstant()).toDays() + 1)
                .coupom(coupom).build().calculate();
    }
}
