package pl.potera.hotel.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import pl.potera.hotel.repository.ReservationsRepository
import pl.potera.hotel.service.ReservationService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest extends Specification {

    private static final String URL = "/reservations"

    @Autowired
    private MockMvc mvc

    @Autowired
    private ReservationsRepository reservationsRepository

    def "should return empty reservations list"() {
        given:
        reservationsRepository.deleteAll()

        when:
        def response = mvc.perform(get(URL))

        then:
        response.andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "[]"
    }
}
