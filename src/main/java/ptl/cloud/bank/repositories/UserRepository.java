package ptl.cloud.bank.repositories;

import org.springframework.data.repository.CrudRepository;
import ptl.cloud.bank.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
