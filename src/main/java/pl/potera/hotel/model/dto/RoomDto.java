package pl.potera.hotel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private long id;

    private String name;

    private RoomTypeDto roomType;
}
