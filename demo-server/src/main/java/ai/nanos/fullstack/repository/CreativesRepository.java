package ai.nanos.fullstack.repository;

import ai.nanos.fullstack.domain.Creatives;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Creatives entity.
 */
@Repository
public interface CreativesRepository extends JpaRepository<Creatives, Long> {

}
