package ai.nanos.fullstack.repository;

import ai.nanos.fullstack.domain.AdCampaignPlatform;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdCampaignPlatform entity.
 */
@Repository
public interface AdCampaignPlatformRepository extends JpaRepository<AdCampaignPlatform, Long> {

}
