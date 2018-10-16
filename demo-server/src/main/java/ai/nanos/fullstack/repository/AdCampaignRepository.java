package ai.nanos.fullstack.repository;

import ai.nanos.fullstack.domain.AdCampaign;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdCampaign entity.
 */
@Repository
public interface AdCampaignRepository extends JpaRepository<AdCampaign, Long> {

	@EntityGraph(attributePaths = "platforms")
	Optional<AdCampaign> findOneWithPlatformsById(Long id);

}
