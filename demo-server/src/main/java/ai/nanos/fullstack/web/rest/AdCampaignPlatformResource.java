package ai.nanos.fullstack.web.rest;

import com.codahale.metrics.annotation.Timed;
import ai.nanos.fullstack.domain.AdCampaignPlatform;
import ai.nanos.fullstack.repository.AdCampaignPlatformRepository;
import ai.nanos.fullstack.web.rest.errors.BadRequestAlertException;
import ai.nanos.fullstack.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AdCampaignPlatform.
 */
@RestController
@RequestMapping("/api")
public class AdCampaignPlatformResource {

    private final Logger log = LoggerFactory.getLogger(AdCampaignPlatformResource.class);

    private static final String ENTITY_NAME = "adCampaignPlatform";

    private final AdCampaignPlatformRepository adCampaignPlatformRepository;

    public AdCampaignPlatformResource(AdCampaignPlatformRepository adCampaignPlatformRepository) {
        this.adCampaignPlatformRepository = adCampaignPlatformRepository;
    }

    /**
     * POST  /ad-campaign-platforms : Create a new adCampaignPlatform.
     *
     * @param adCampaignPlatform the adCampaignPlatform to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adCampaignPlatform, or with status 400 (Bad Request) if the adCampaignPlatform has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ad-campaign-platforms")
    @Timed
    public ResponseEntity<AdCampaignPlatform> createAdCampaignPlatform(@RequestBody AdCampaignPlatform adCampaignPlatform) throws URISyntaxException {
        log.debug("REST request to save AdCampaignPlatform : {}", adCampaignPlatform);
        if (adCampaignPlatform.getId() != null) {
            throw new BadRequestAlertException("A new adCampaignPlatform cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdCampaignPlatform result = adCampaignPlatformRepository.save(adCampaignPlatform);
        return ResponseEntity.created(new URI("/api/ad-campaign-platforms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ad-campaign-platforms : Updates an existing adCampaignPlatform.
     *
     * @param adCampaignPlatform the adCampaignPlatform to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adCampaignPlatform,
     * or with status 400 (Bad Request) if the adCampaignPlatform is not valid,
     * or with status 500 (Internal Server Error) if the adCampaignPlatform couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ad-campaign-platforms")
    @Timed
    public ResponseEntity<AdCampaignPlatform> updateAdCampaignPlatform(@RequestBody AdCampaignPlatform adCampaignPlatform) throws URISyntaxException {
        log.debug("REST request to update AdCampaignPlatform : {}", adCampaignPlatform);
        if (adCampaignPlatform.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdCampaignPlatform result = adCampaignPlatformRepository.save(adCampaignPlatform);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adCampaignPlatform.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ad-campaign-platforms : get all the adCampaignPlatforms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of adCampaignPlatforms in body
     */
    @GetMapping("/ad-campaign-platforms")
    @Timed
    public List<AdCampaignPlatform> getAllAdCampaignPlatforms() {
        log.debug("REST request to get all AdCampaignPlatforms");
        return adCampaignPlatformRepository.findAll();
    }

    /**
     * GET  /ad-campaign-platforms/:id : get the "id" adCampaignPlatform.
     *
     * @param id the id of the adCampaignPlatform to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adCampaignPlatform, or with status 404 (Not Found)
     */
    @GetMapping("/ad-campaign-platforms/{id}")
    @Timed
    public ResponseEntity<AdCampaignPlatform> getAdCampaignPlatform(@PathVariable Long id) {
        log.debug("REST request to get AdCampaignPlatform : {}", id);
        Optional<AdCampaignPlatform> adCampaignPlatform = adCampaignPlatformRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(adCampaignPlatform);
    }

    /**
     * DELETE  /ad-campaign-platforms/:id : delete the "id" adCampaignPlatform.
     *
     * @param id the id of the adCampaignPlatform to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ad-campaign-platforms/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdCampaignPlatform(@PathVariable Long id) {
        log.debug("REST request to delete AdCampaignPlatform : {}", id);

        adCampaignPlatformRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
