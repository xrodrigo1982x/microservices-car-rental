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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${cassandra.keyspace:price}")
    private String keyspace;
    @Value("${cassandra.contactpoints:localhost}")
    private String contactpoints;
    @Value("${cassandra.port:9042}")
    private Integer port;

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    @Bean
    public CassandraClusterFactoryBean cluster() {
        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactpoints);
        cluster.setPort(port);
        return cluster;
    }

    @Override
    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        return new BasicCassandraMappingContext();
    }



    @Component
    @ConditionalOnProperty("cassandra.populate")
    public static class Populate{

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

}
