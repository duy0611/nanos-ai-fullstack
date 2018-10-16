package ai.nanos.fullstack.repository;

import ai.nanos.fullstack.domain.Audiance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Audiance entity.
 */
@Repository
public interface AudianceRepository extends JpaRepository<Audiance, Long> {

    @Query(value = "select distinct audiance from Audiance audiance left join fetch audiance.languages left join fetch audiance.locations left join fetch audiance.interests",
        countQuery = "select count(distinct audiance) from Audiance audiance")
    Page<Audiance> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct audiance from Audiance audiance left join fetch audiance.languages left join fetch audiance.locations left join fetch audiance.interests")
    List<Audiance> findAllWithEagerRelationships();

    @Query("select audiance from Audiance audiance left join fetch audiance.languages left join fetch audiance.locations left join fetch audiance.interests where audiance.id =:id")
    Optional<Audiance> findOneWithEagerRelationships(@Param("id") Long id);

}
