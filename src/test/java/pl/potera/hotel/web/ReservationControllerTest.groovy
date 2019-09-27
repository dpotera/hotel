package pl.potera.hotel.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import pl.potera.hotel.model.dto.ReservationDto
import pl.potera.hotel.model.dto.RoomDto
import pl.potera.hotel.model.dto.RoomTypeDto
import pl.potera.hotel.model.dto.UserDto
import pl.potera.hotel.model.request.ReservationRequest
import pl.potera.hotel.repository.ReservationsRepository
import pl.potera.hotel.service.ReservationService
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest extends Specification {

    private static final String URL = "/reservations"

    @Autowired
    private MockMvc mvc

    @Autowired
    private ReservationService reservationService
    @Autowired
    private ReservationsRepository reservationsRepository
    @Autowired
    ObjectMapper objectMapper

    def "should return empty reservations list"() {
        when:
        def response = mvc.perform(get(URL))

        then:
        response.andExpect(status().isOk())
    }

    def "should create reservation and return reservations for given room number"() {
        given:
        def reservationRequest = prepareReservationRequest()
        def reservationDto = reservationService.create(reservationRequest)

        when:
        def response = mvc.perform(get(URL).param("roomNumber", reservationDto.room.id as String))

        then:
        def result = response.andExpect(status().isOk())
        responseToList(result) == [reservationDto]
    }

    def responseToList(ResultActions result) {
        Arrays.asList(objectMapper.readValue(result.andReturn().response.contentAsString, ReservationDto[].class))
    }

    private static def prepareReservationRequest() {
        return new ReservationRequest(
                1,
                2,
                LocalDate.parse("2019-09-01"),
                LocalDate.parse("2019-09-05")
        )
    }
}
