package microservices.book.multiplication.repository;

import microservices.book.multiplication.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface allows us to save and retrieve Users
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAlias(final String alias);
}