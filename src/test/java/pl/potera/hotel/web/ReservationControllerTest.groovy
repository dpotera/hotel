package pl.potera.hotel.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import pl.potera.hotel.repository.ReservationsRepository
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
    ReservationsRepository reservationsRepository

    def "should return empty reservations list"() {
        expect:
        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString == "[]"
    }
}
