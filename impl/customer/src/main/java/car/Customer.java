package car;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

@Data
public class Customer {

    @Id
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;

}
