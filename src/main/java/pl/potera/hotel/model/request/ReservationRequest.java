package pl.potera.hotel.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReservationRequest {

    @NotNull(message = "User id must be provided")
    private long userId;

    @Size(min = 1, max = 8, message = "Number of people must be at least 1 and at most 8")
    private int numberOfPeople;

    @NotNull(message = "Start date must be provided")
    private LocalDate startDate;

    @NotNull(message = "End date must be provided")
    private LocalDate endDate;
}
