package car;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Car {

    @Id
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String brand;
    private List<String> features;
    @NotEmpty
    private String category;

}
