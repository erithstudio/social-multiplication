package microservices.template.multiplication.repository;

import microservices.template.multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface allows us to save and retrieve
 * Multiplications
 */
@Repository
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}