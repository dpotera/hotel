package pl.potera.hotel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private UUID id;

    private UserDto user;

    private RoomDto room;

    private int numberOfPeople;

    private LocalDate startDate;

    private LocalDate endDate;
}
