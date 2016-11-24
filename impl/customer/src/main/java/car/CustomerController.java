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
public class CustomerController {

    private CustomerRepository customerRepository;

    @RequestMapping("{customer}")
    public ResponseEntity findOne(@PathVariable Customer customer) {
        return customer != null ? ok(customer) : notFound().build();
    }

    @RequestMapping(method = POST)
    public ResponseEntity create(@RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        return ok(customerRepository.save(customer));
    }

    @RequestMapping(value="{id}", method = PUT)
    public ResponseEntity update(@PathVariable String id, @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()) return unprocessableEntity().build();
        customer.setId(id);
        return ok(customerRepository.save(customer));
    }

    @RequestMapping(value = "{customer}", method = DELETE)
    public void delete(@PathVariable Customer customer){
        customerRepository.delete(customer);
    }

}
