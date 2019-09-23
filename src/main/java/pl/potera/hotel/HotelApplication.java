package pl.potera.hotel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import pl.potera.hotel.repository.RoomTypesRepository;
import pl.potera.hotel.repository.RoomsRepository;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoomTypesRepository roomTypesRepository, RoomsRepository roomsRepository) {
		return args -> {
			printAll(roomTypesRepository);
			printAll(roomsRepository);
		};
	}

	private <T, ID> void printAll(CrudRepository<T, ID> repository) {
		repository.findAll().forEach(System.out::println);
	}
}
