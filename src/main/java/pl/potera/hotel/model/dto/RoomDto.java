package pl.potera.hotel.model.dto;

import lombok.Data;

@Data
public class RoomDto {

    private long id;

    private String name;

    private RoomTypeDto roomType;
}
