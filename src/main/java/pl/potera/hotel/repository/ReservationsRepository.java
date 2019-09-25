package pl.potera.hotel.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potera.hotel.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationsRepository extends CrudRepository<Reservation, UUID> {

    List<Reservation> findAll();
    List<Reservation> findAllByRoomId(long id);
}
