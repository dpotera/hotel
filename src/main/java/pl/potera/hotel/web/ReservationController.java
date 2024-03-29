package pl.potera.hotel.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.potera.hotel.model.dto.ReservationDto;
import pl.potera.hotel.model.request.ReservationRequest;
import pl.potera.hotel.service.ReservationService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationDto> get(@RequestParam(required = false) String roomNumber) {
        if (roomNumber == null || roomNumber.isEmpty()) {
            return reservationService.findAll();
        } else {
            return reservationService.getForRoom(Long.parseLong(roomNumber));
        }
    }

    @GetMapping("{uuid}")
    public ReservationDto getById(@PathVariable UUID uuid) {
        return reservationService.get(uuid);
    }

    @PostMapping
    public ReservationDto create(@Valid @RequestBody ReservationRequest reservationRequest) {
        return reservationService.create(reservationRequest);
    }

    @DeleteMapping("{uuid}")
    public void delete(@PathVariable UUID uuid) {
        reservationService.delete(uuid);
    }
}
