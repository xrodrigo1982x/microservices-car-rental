package car;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
public class Location {

    @Id
    private String id;
    @NotEmpty
    private String name;
    @NotNull
    private Double[] coordinates;

}
