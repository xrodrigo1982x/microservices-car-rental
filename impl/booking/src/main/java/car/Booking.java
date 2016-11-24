package car;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class Booking {

    @Id
    private String id;
    @NotEmpty
    private String customer;
    @NotEmpty
    private String car;
    @NotEmpty
    private String pickUpLocation;
    @NotEmpty
    private String dropOffLocation;
    @NotNull
    private Date pickUpDate;
    @NotNull
    private Date dropOffDate;
    private List<String> offers;

}
