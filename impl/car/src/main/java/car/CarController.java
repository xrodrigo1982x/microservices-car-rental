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
public class CarController {

    private CarRepository carRepository;

    @RequestMapping("{car}")
    public ResponseEntity findOne(@PathVariable Car car) {
        return car != null ? ok(car) : notFound().build();
    }

    @RequestMapping(method = POST)
    public ResponseEntity create(@RequestBody Car car, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        return ok(carRepository.save(car));
    }

    @RequestMapping(value="{id}", method = PUT)
    public ResponseEntity update(@PathVariable String id, @RequestBody Car car, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        car.setId(id);
        return ok(carRepository.save(car));
    }

    @RequestMapping(value = "{car}", method = DELETE)
    public void delete(@PathVariable Car car){
        carRepository.delete(car);
    }

}
