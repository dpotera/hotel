package pl.potera.hotel.model.dto;

import lombok.Data;
import pl.potera.hotel.model.User;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ReservationDto {

    private UUID id;

    private User user;

    private RoomDto room;

    private int numberOfPeople;

    private LocalDate startDate;

    private LocalDate endDate;
}
