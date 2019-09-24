package pl.potera.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.potera.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomsRepository extends CrudRepository<Room, Long> {

    @Query("SELECT r FROM Room r JOIN RoomType rt ON r.roomType=rt WHERE " +
            "rt.capacity >= :numberOfPeople AND r NOT IN (" +
            "SELECT re.room FROM Reservation re WHERE " +
            "re.startDate <= :endDate OR re.endDate >= :startDate" +
            ") ORDER BY rt.capacity DESC")
    List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, int numberOfPeople);
}
