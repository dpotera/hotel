package pl.potera.hotel.model.dto;

import lombok.Data;

@Data
public class RoomTypeDto {

    private long id;

    private String name;

    private int capacity;
}