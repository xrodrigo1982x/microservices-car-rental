package car.service;

import car.model.Pricing;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    public Pricing pricing(Pricing pricing) {
        return pricing;
    }
}
