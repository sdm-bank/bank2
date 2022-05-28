package ptl.cloud.bank.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ptl.cloud.bank.repositories.OwnerSearchableRepository;
import ptl.cloud.bank.security.CustomUserDetailsService;

import java.util.List;

public class BasicService<T, R extends OwnerSearchableRepository<T, Long>> {
    protected final R repository;
    protected final CustomUserDetailsService userDetailsService;

    public BasicService(R repository, CustomUserDetailsService userDetailsService) {
        this.repository = repository;
        this.userDetailsService = userDetailsService;
    }

    public List<T> getAll() {
        return repository.findAllByOwner(userDetailsService.getUser());
    }

    public T findById(Long id) {
        return repository.findAllByIdAndOwner(id, userDetailsService.getUser()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no entity with id: " + id));
    }

    public T save(T entity) {
        return repository.save(entity);
    }
}
