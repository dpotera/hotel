package pl.potera.hotel.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potera.hotel.model.RoomType;

public interface RoomTypesRepository extends CrudRepository<RoomType, Long> {
}
