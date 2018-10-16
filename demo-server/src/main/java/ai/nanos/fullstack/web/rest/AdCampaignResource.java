package ai.nanos.fullstack.web.rest;

import com.codahale.metrics.annotation.Timed;
import ai.nanos.fullstack.domain.AdCampaign;
import ai.nanos.fullstack.repository.AdCampaignRepository;
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
 * REST controller for managing AdCampaign.
 */
@RestController
@RequestMapping("/api")
public class AdCampaignResource {

    private final Logger log = LoggerFactory.getLogger(AdCampaignResource.class);

    private static final String ENTITY_NAME = "adCampaign";

    private final AdCampaignRepository adCampaignRepository;

    public AdCampaignResource(AdCampaignRepository adCampaignRepository) {
        this.adCampaignRepository = adCampaignRepository;
    }

    /**
     * POST  /ad-campaigns : Create a new adCampaign.
     *
     * @param adCampaign the adCampaign to create
     * @return the ResponseEntity with status 201 (Created) and with body the new adCampaign, or with status 400 (Bad Request) if the adCampaign has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ad-campaigns")
    @Timed
    public ResponseEntity<AdCampaign> createAdCampaign(@RequestBody AdCampaign adCampaign) throws URISyntaxException {
        log.debug("REST request to save AdCampaign : {}", adCampaign);
        if (adCampaign.getId() != null) {
            throw new BadRequestAlertException("A new adCampaign cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdCampaign result = adCampaignRepository.save(adCampaign);
        return ResponseEntity.created(new URI("/api/ad-campaigns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ad-campaigns : Updates an existing adCampaign.
     *
     * @param adCampaign the adCampaign to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated adCampaign,
     * or with status 400 (Bad Request) if the adCampaign is not valid,
     * or with status 500 (Internal Server Error) if the adCampaign couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ad-campaigns")
    @Timed
    public ResponseEntity<AdCampaign> updateAdCampaign(@RequestBody AdCampaign adCampaign) throws URISyntaxException {
        log.debug("REST request to update AdCampaign : {}", adCampaign);
        if (adCampaign.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdCampaign result = adCampaignRepository.save(adCampaign);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, adCampaign.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ad-campaigns : get all the adCampaigns.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of adCampaigns in body
     */
    @GetMapping("/ad-campaigns")
    @Timed
    public List<AdCampaign> getAllAdCampaigns() {
        log.debug("REST request to get all AdCampaigns");
        return adCampaignRepository.findAll();
    }

    /**
     * GET  /ad-campaigns/:id : get the "id" adCampaign.
     *
     * @param id the id of the adCampaign to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the adCampaign, or with status 404 (Not Found)
     */
    @GetMapping("/ad-campaigns/{id}")
    @Timed
    public ResponseEntity<AdCampaign> getAdCampaign(@PathVariable Long id) {
        log.debug("REST request to get AdCampaign : {}", id);
        Optional<AdCampaign> adCampaign = adCampaignRepository.findOneWithPlatformsById(id);
        return ResponseUtil.wrapOrNotFound(adCampaign);
    }

    /**
     * DELETE  /ad-campaigns/:id : delete the "id" adCampaign.
     *
     * @param id the id of the adCampaign to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ad-campaigns/{id}")
    @Timed
    public ResponseEntity<Void> deleteAdCampaign(@PathVariable Long id) {
        log.debug("REST request to delete AdCampaign : {}", id);

        adCampaignRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
