package pl.potera.hotel.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.potera.hotel.exception.NoRoomsAvailableException;
import pl.potera.hotel.exception.UserNotFoundException;
import pl.potera.hotel.model.Reservation;
import pl.potera.hotel.model.Room;
import pl.potera.hotel.model.User;
import pl.potera.hotel.model.dto.ReservationDto;
import pl.potera.hotel.model.request.ReservationRequest;
import pl.potera.hotel.repository.ReservationsRepository;
import pl.potera.hotel.repository.RoomsRepository;
import pl.potera.hotel.repository.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ModelMapper modelMapper;
    private final ReservationsRepository repository;
    private final UsersRepository usersRepository;
    private final RoomsRepository roomsRepository;

    public List<ReservationDto> findAll() {
        return repository.findAll().stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }

    public ReservationDto create(ReservationRequest reservationRequest) {
        Optional<User> user = usersRepository.findById(reservationRequest.getUserId());
        if (user.isPresent()) {
            Optional<Room> room = getRoom(reservationRequest);
            if (room.isPresent()) {
                Reservation reservation = new Reservation(
                        UUID.randomUUID(),
                        user.get(),
                        room.get(),
                        reservationRequest.getNumberOfPeople(),
                        reservationRequest.getStartDate(),
                        reservationRequest.getEndDate());
                repository.save(reservation);
                return modelMapper.map(reservation, ReservationDto.class);
            } else {
                throw new NoRoomsAvailableException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    private Optional<Room> getRoom(ReservationRequest reservationRequest) {
        List<Room> availableRooms = roomsRepository.findAvailableRooms(
                reservationRequest.getStartDate(),
                reservationRequest.getEndDate(),
                reservationRequest.getNumberOfPeople()
        );
        if (availableRooms.size() > 0) {
            return Optional.of(availableRooms.get(0));
        } else {
            return Optional.empty();
        }
    }

    public ReservationDto get(UUID id) {
        return repository.findById(id)
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .get();
    }
}
