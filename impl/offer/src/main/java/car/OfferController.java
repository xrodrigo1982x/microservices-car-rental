package car;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OfferController {

    private OfferRepository offerRepository;

    @RequestMapping("{offer}")
    public ResponseEntity findOne(@PathVariable Offer offer) {
        return offer != null ? ok(offer) : notFound().build();
    }

    @RequestMapping(method = POST)
    public ResponseEntity create(@RequestBody Offer offer, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        return ok(offerRepository.save(offer));
    }

    @RequestMapping(value="{id}", method = PUT)
    public ResponseEntity update(@PathVariable String id, @RequestBody Offer offer, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        offer.setId(id);
        return ok(offerRepository.save(offer));
    }

    @RequestMapping(value = "{offer}", method = DELETE)
    public void delete(@PathVariable Offer offer){
        offerRepository.delete(offer);
    }

}
