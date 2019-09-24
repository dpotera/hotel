package pl.potera.hotel.repository;

import org.springframework.data.repository.CrudRepository;
import pl.potera.hotel.model.User;

public interface UsersRepository extends CrudRepository<User, Long> {
}
