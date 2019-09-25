package pl.potera.hotel.service

import org.modelmapper.ModelMapper
import pl.potera.hotel.model.Room
import pl.potera.hotel.model.RoomType
import pl.potera.hotel.model.User
import pl.potera.hotel.model.request.ReservationRequest
import pl.potera.hotel.repository.ReservationsRepository
import pl.potera.hotel.repository.RoomsRepository
import pl.potera.hotel.repository.UsersRepository
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class ReservationServiceTest extends Specification {

    @Subject
    ReservationService reservationService
    def usersRepository = Mock(UsersRepository)
    def reservationsRepository = Mock(ReservationsRepository)
    def roomsRepository = Mock(RoomsRepository)

    def setup() {
        reservationService = new ReservationService(
                new ModelMapper(),
                reservationsRepository,
                usersRepository,
                roomsRepository
        )
    }

    def "FindAll"() {
    }

    def "should save reservation"() {
        given:
        def user = prepareUser()
        def room1 = prepareRoom()
        def reservationRequest = prepareReservationRequest(user.id)
        usersRepository.findById(user.id) >> Optional.of(user)
        roomsRepository.findAvailableRooms(
                reservationRequest.startDate, reservationRequest.endDate, reservationRequest.numberOfPeople
        ) >> [ room1 ]

        when:
        def reservationDto = reservationService.save(reservationRequest)

        then:
        reservationDto.id != null
        reservationDto.room.id == room1.id
        reservationDto.room.name == room1.name
        reservationDto.room.roomType.id == room1.roomType.id
        reservationDto.room.roomType.capacity == room1.roomType.capacity
        reservationDto.startDate == reservationRequest.startDate
        reservationDto.endDate == reservationRequest.endDate
        reservationDto.user.id == reservationRequest.userId
        reservationDto.user.name == user.name
    }

    def prepareReservationRequest(long userId) {
        def startDate = LocalDate.parse('2019-09-01')
        def endDate = LocalDate.parse('2019-09-06')
        def numberOfPeople = 2
        new ReservationRequest(userId, numberOfPeople, startDate, endDate)
    }

    def prepareRoom() {
        return new Room(1, "test room 1", new RoomType(1, "small", 4), [] as Set)
    }

    def prepareUser() {
        return new User(1, "test user")
    }
}
