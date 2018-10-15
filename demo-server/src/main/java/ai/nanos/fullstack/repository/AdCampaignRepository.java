package ai.nanos.fullstack.repository;

import ai.nanos.fullstack.domain.AdCampaign;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdCampaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdCampaignRepository extends JpaRepository<AdCampaign, Long> {

}
