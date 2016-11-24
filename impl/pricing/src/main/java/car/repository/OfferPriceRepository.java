package car.repository;

import car.model.OfferPrice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OfferPriceRepository extends PagingAndSortingRepository<OfferPrice, String> {
}
