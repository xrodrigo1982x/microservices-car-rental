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
public class BookingController {

    private BookingRepository bookingRepository;

    @RequestMapping("{booking}")
    public ResponseEntity findOne(@PathVariable Booking booking) {
        return booking != null ? ok(booking) : notFound().build();
    }

    @RequestMapping(method = POST)
    public ResponseEntity create(@RequestBody Booking booking, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        return ok(bookingRepository.save(booking));
    }

    @RequestMapping(value="{id}", method = PUT)
    public ResponseEntity update(@PathVariable String id, @RequestBody Booking booking, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        booking.setId(id);
        return ok(bookingRepository.save(booking));
    }

    @RequestMapping(value = "{booking}", method = DELETE)
    public void delete(@PathVariable Booking booking){
        bookingRepository.delete(booking);
    }

}
