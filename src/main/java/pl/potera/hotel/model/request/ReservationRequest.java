package pl.potera.hotel.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReservationRequest {

    @NotNull
    private long userId;

    @Min(1)
    @Max(8)
    private int numberOfPeople;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
