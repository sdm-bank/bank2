package ptl.cloud.bank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ptl.cloud.bank.entities.User;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface OwnerSearchableRepository<T, ID> extends CrudRepository<T, ID> {
    List<T> findAllByOwner(User user);
    Optional<T> findAllByIdAndOwner(ID id, User owner);
}
