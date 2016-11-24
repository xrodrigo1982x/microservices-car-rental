package car.controller;

import car.model.Pricing;
import car.service.PricingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.unprocessableEntity;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PrincingController {

    private PricingService pricingService;

    @RequestMapping(method = POST)
    public ResponseEntity create(@RequestBody Pricing pricing, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        return ok(pricingService.pricing(pricing));
    }

}
