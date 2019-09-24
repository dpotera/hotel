package pl.potera.hotel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import pl.potera.hotel.model.request.ReservationRequest;
import pl.potera.hotel.repository.RoomTypesRepository;
import pl.potera.hotel.repository.RoomsRepository;
import pl.potera.hotel.service.ReservationService;

import java.time.LocalDate;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoomTypesRepository roomTypesRepository, RoomsRepository roomsRepository,
						   ReservationService reservationService) {
		return args -> {
			printAll(roomTypesRepository);
			printAll(roomsRepository);
			reservationService.save(new ReservationRequest(1, 2, LocalDate.parse("2019-05-01"), LocalDate.parse("2019-05-05")));
			reservationService.save(new ReservationRequest(1, 2, LocalDate.parse("2019-05-01"), LocalDate.parse("2019-05-05")));
			reservationService.save(new ReservationRequest(1, 2, LocalDate.parse("2019-05-01"), LocalDate.parse("2019-05-05")));
		};
	}

	private <T, ID> void printAll(CrudRepository<T, ID> repository) {
		repository.findAll().forEach(System.out::println);
	}
}
