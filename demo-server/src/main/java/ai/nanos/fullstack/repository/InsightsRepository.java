package ai.nanos.fullstack.repository;

import ai.nanos.fullstack.domain.Insights;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Insights entity.
 */
@Repository
public interface InsightsRepository extends JpaRepository<Insights, Long> {

}
