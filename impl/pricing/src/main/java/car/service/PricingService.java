package car.service;

import car.model.*;
import car.repository.CarRepository;
import car.repository.CoupomRepository;
import car.repository.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PricingService {

    private CarRepository carRepository;
    private CoupomRepository coupomRepository;
    private OfferRepository offerRepository;

    public PriceDetails pricing(Pricing pricing) {
        Car car = carRepository.findOne(pricing.getCar());
        Coupom coupom = pricing.getCoupon() != null ? coupomRepository.findOne(pricing.getCoupon()) : null;
        List<Offer> offers = pricing.getOffers() != null && !pricing.getOffers().isEmpty() ? (List<Offer>) offerRepository.findAll(pricing.getOffers()) : null;
        return new PriceDetails(car, pricing.getLocation().getPickUp(), pricing.getLocation().getDropOff(), pricing.getPickUpDate(), pricing.getDropOffDate(), offers, coupom);
    }
}
