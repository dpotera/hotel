package pl.potera.hotel.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import pl.potera.hotel.model.dto.ReservationDto
import pl.potera.hotel.model.dto.RoomDto
import pl.potera.hotel.model.dto.RoomTypeDto
import pl.potera.hotel.model.dto.UserDto
import pl.potera.hotel.service.ReservationService
import spock.lang.Ignore
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(properties= "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
class ReservationControllerTest extends Specification {

    private static final String URL = "/reservations"

    @Autowired
    private MockMvc mvc

    @Autowired
    private ObjectMapper objectMapper

    @Autowired
    private ReservationService reservationService

    def "should return empty reservations list"() {
        given:
        reservationService.findAll() >> []

        when:
        def response = mvc.perform(get(URL))

        then:
        response.andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "[]"
    }

//    def "should return reservations for given room number"() {
//        given:
//        def roomNumber = 1
//        def reservationDto = prepareReservationDto(roomNumber)
//        reservationService.getForRoom(roomNumber) >> [ reservationDto ]
//
//        when:
//        def response = mvc.perform(get(URL).param("roomNumber", roomNumber as String))
//
//        then:
//        response.andExpect(status().isOk())
//                .andReturn()
//                .response
//                .contentAsString == objectMapper.writeValueAsString(reservationDto)
//    }

    @TestConfiguration
    static class StubConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        ReservationService reservationService() {
            return detachedMockFactory.Stub(ReservationService)
        }
    }

    private static def prepareReservationDto(long roomNumber) {
        return new ReservationDto(
                UUID.randomUUID(),
                new UserDto(1, "test"),
                new RoomDto(roomNumber, "test room", new RoomTypeDto(1, "small", 4)),
                2,
                LocalDate.parse("2019-09-01"),
                LocalDate.parse("2019-09-05")
        )
    }
}
