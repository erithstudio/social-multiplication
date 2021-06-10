package microservices.template.gamification.repository;

import microservices.template.gamification.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Handles data operations with BadgeCards
 */
@Repository
public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {
    /**
     * Retrieves all BadgeCards for a given user.
     *
     * @param userId the id of the user to look for BadgeCards
     * @return the list of BadgeCards, sorted by most recent.
     */
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
