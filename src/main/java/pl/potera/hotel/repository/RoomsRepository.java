package pl.potera.hotel.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potera.hotel.model.Room;

public interface RoomsRepository extends CrudRepository<Room, Long> {
}
