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
public class LocationController {

    private LocationRepository locationRepository;

    @RequestMapping("{location}")
    public ResponseEntity findOne(@PathVariable Location location) {
        return location != null ? ok(location) : notFound().build();
    }

    @RequestMapping(method = POST)
    public ResponseEntity create(@RequestBody Location location, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        return ok(locationRepository.save(location));
    }

    @RequestMapping(value="{id}", method = PUT)
    public ResponseEntity update(@PathVariable String id, @RequestBody Location location, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        location.setId(id);
        return ok(locationRepository.save(location));
    }

    @RequestMapping(value = "{location}", method = DELETE)
    public void delete(@PathVariable Location location){
        locationRepository.delete(location);
    }

}
