package pl.potera.hotel.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.potera.hotel.exception.NoRoomsAvailableException;
import pl.potera.hotel.exception.UserNotFoundException;
import pl.potera.hotel.kafka.KafkaClient;
import pl.potera.hotel.model.Reservation;
import pl.potera.hotel.model.Room;
import pl.potera.hotel.model.User;
import pl.potera.hotel.model.dto.ReservationDto;
import pl.potera.hotel.model.request.ReservationRequest;
import pl.potera.hotel.repository.ReservationsRepository;
import pl.potera.hotel.repository.RoomsRepository;
import pl.potera.hotel.repository.UsersRepository;

import java.util.ArrayList;
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
    private final KafkaClient kafkaClient;

    public List<ReservationDto> findAll() {
        return mapToDtos(repository.findAll());
    }

    public ReservationDto create(ReservationRequest reservationRequest) {
        User user = getUser(reservationRequest.getUserId());
        Room room = getRoom(reservationRequest);
        Reservation reservation = buildReservation(reservationRequest, user, room);
        repository.save(reservation);
        ReservationDto reservationDto = modelMapper.map(reservation, ReservationDto.class);
        kafkaClient.sendMessage(reservationDto.getId().toString(), reservationDto.getRoom().getRoomType().getName());
        return reservationDto;
    }

    public List<ReservationDto> getForRoom(long id) {
        return mapToDtos(repository.findAllByRoomId(id));
    }

    public ReservationDto get(UUID id) {
        return repository.findById(id)
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .get();
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private User getUser(long userId) {
        Optional<User> user = usersRepository.findById(userId);
        return user.orElseThrow(UserNotFoundException::new);
    }

    private Room getRoom(ReservationRequest reservationRequest) {
        List<Room> availableRooms = roomsRepository.findAvailableRooms(
                reservationRequest.getStartDate(),
                reservationRequest.getEndDate(),
                reservationRequest.getNumberOfPeople()
        );
        if (availableRooms.size() > 0) {
            return availableRooms.get(0);
        } else {
            throw new NoRoomsAvailableException();
        }
    }

    private Reservation buildReservation(ReservationRequest reservationRequest, User user, Room room) {
        return Reservation.builder()
                .user(user)
                .room(room)
                .numberOfPeople(reservationRequest.getNumberOfPeople())
                .startDate(reservationRequest.getStartDate())
                .endDate(reservationRequest.getEndDate())
                .build();
    }

    private List<ReservationDto> mapToDtos(List<Reservation> reservations) {
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }
}
