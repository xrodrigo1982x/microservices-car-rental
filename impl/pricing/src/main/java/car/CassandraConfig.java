package car;

import car.model.Car;
import car.model.Coupom;
import car.model.Offer;
import car.repository.CarRepository;
import car.repository.CoupomRepository;
import car.repository.OfferRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@ConditionalOnProperty("cassandra.populate")
public class CassandraConfig{

    @Value("${cassandra.populate:false}")
    private Boolean populate;

    @Data
    private static class PopulateClass{
        private List<Car> car;
        private List<Coupom> coupom;
        private List<Offer> offer;
    }

    @Autowired
    public void populate(CarRepository carRepository, CoupomRepository coupomRepository, OfferRepository offerRepository, ObjectMapper om) throws IOException {
        if(populate){
            PopulateClass data = om.readValue(this.getClass().getResourceAsStream(File.separator + "data.json"), PopulateClass.class);
            carRepository.save(data.car);
            offerRepository.save(data.offer);
            coupomRepository.save(data.coupom);
        }
    }

}