package ai.nanos.fullstack.web.rest;

import ai.nanos.fullstack.DemoApp;

import ai.nanos.fullstack.domain.AdCampaignPlatform;
import ai.nanos.fullstack.repository.AdCampaignPlatformRepository;
import ai.nanos.fullstack.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static ai.nanos.fullstack.web.rest.TestUtil.sameInstant;
import static ai.nanos.fullstack.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ai.nanos.fullstack.domain.enumeration.AdCampaignPlatformType;
import ai.nanos.fullstack.domain.enumeration.AdCampaignStatus;
/**
 * Test class for the AdCampaignPlatformResource REST controller.
 *
 * @see AdCampaignPlatformResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApp.class)
public class AdCampaignPlatformResourceIntTest {

    private static final AdCampaignPlatformType DEFAULT_TYPE = AdCampaignPlatformType.Facebook;
    private static final AdCampaignPlatformType UPDATED_TYPE = AdCampaignPlatformType.Instagram;

    private static final AdCampaignStatus DEFAULT_STATUS = AdCampaignStatus.Scheduled;
    private static final AdCampaignStatus UPDATED_STATUS = AdCampaignStatus.Delivering;

    private static final Integer DEFAULT_TOTAL_BUDGET = 1;
    private static final Integer UPDATED_TOTAL_BUDGET = 2;

    private static final Integer DEFAULT_REMAINING_BUDGET = 1;
    private static final Integer UPDATED_REMAINING_BUDGET = 2;

    private static final ZonedDateTime DEFAULT_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private AdCampaignPlatformRepository adCampaignPlatformRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdCampaignPlatformMockMvc;

    private AdCampaignPlatform adCampaignPlatform;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdCampaignPlatformResource adCampaignPlatformResource = new AdCampaignPlatformResource(adCampaignPlatformRepository);
        this.restAdCampaignPlatformMockMvc = MockMvcBuilders.standaloneSetup(adCampaignPlatformResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdCampaignPlatform createEntity(EntityManager em) {
        AdCampaignPlatform adCampaignPlatform = new AdCampaignPlatform()
            .type(DEFAULT_TYPE)
            .status(DEFAULT_STATUS)
            .totalBudget(DEFAULT_TOTAL_BUDGET)
            .remainingBudget(DEFAULT_REMAINING_BUDGET)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return adCampaignPlatform;
    }

    @Before
    public void initTest() {
        adCampaignPlatform = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdCampaignPlatform() throws Exception {
        int databaseSizeBeforeCreate = adCampaignPlatformRepository.findAll().size();

        // Create the AdCampaignPlatform
        restAdCampaignPlatformMockMvc.perform(post("/api/ad-campaign-platforms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adCampaignPlatform)))
            .andExpect(status().isCreated());

        // Validate the AdCampaignPlatform in the database
        List<AdCampaignPlatform> adCampaignPlatformList = adCampaignPlatformRepository.findAll();
        assertThat(adCampaignPlatformList).hasSize(databaseSizeBeforeCreate + 1);
        AdCampaignPlatform testAdCampaignPlatform = adCampaignPlatformList.get(adCampaignPlatformList.size() - 1);
        assertThat(testAdCampaignPlatform.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAdCampaignPlatform.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAdCampaignPlatform.getTotalBudget()).isEqualTo(DEFAULT_TOTAL_BUDGET);
        assertThat(testAdCampaignPlatform.getRemainingBudget()).isEqualTo(DEFAULT_REMAINING_BUDGET);
        assertThat(testAdCampaignPlatform.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testAdCampaignPlatform.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createAdCampaignPlatformWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adCampaignPlatformRepository.findAll().size();

        // Create the AdCampaignPlatform with an existing ID
        adCampaignPlatform.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdCampaignPlatformMockMvc.perform(post("/api/ad-campaign-platforms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adCampaignPlatform)))
            .andExpect(status().isBadRequest());

        // Validate the AdCampaignPlatform in the database
        List<AdCampaignPlatform> adCampaignPlatformList = adCampaignPlatformRepository.findAll();
        assertThat(adCampaignPlatformList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdCampaignPlatforms() throws Exception {
        // Initialize the database
        adCampaignPlatformRepository.saveAndFlush(adCampaignPlatform);

        // Get all the adCampaignPlatformList
        restAdCampaignPlatformMockMvc.perform(get("/api/ad-campaign-platforms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adCampaignPlatform.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].totalBudget").value(hasItem(DEFAULT_TOTAL_BUDGET)))
            .andExpect(jsonPath("$.[*].remainingBudget").value(hasItem(DEFAULT_REMAINING_BUDGET)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(sameInstant(DEFAULT_START_DATE))))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(sameInstant(DEFAULT_END_DATE))));
    }
    
    @Test
    @Transactional
    public void getAdCampaignPlatform() throws Exception {
        // Initialize the database
        adCampaignPlatformRepository.saveAndFlush(adCampaignPlatform);

        // Get the adCampaignPlatform
        restAdCampaignPlatformMockMvc.perform(get("/api/ad-campaign-platforms/{id}", adCampaignPlatform.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adCampaignPlatform.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.totalBudget").value(DEFAULT_TOTAL_BUDGET))
            .andExpect(jsonPath("$.remainingBudget").value(DEFAULT_REMAINING_BUDGET))
            .andExpect(jsonPath("$.startDate").value(sameInstant(DEFAULT_START_DATE)))
            .andExpect(jsonPath("$.endDate").value(sameInstant(DEFAULT_END_DATE)));
    }

    @Test
    @Transactional
    public void getNonExistingAdCampaignPlatform() throws Exception {
        // Get the adCampaignPlatform
        restAdCampaignPlatformMockMvc.perform(get("/api/ad-campaign-platforms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdCampaignPlatform() throws Exception {
        // Initialize the database
        adCampaignPlatformRepository.saveAndFlush(adCampaignPlatform);

        int databaseSizeBeforeUpdate = adCampaignPlatformRepository.findAll().size();

        // Update the adCampaignPlatform
        AdCampaignPlatform updatedAdCampaignPlatform = adCampaignPlatformRepository.findById(adCampaignPlatform.getId()).get();
        // Disconnect from session so that the updates on updatedAdCampaignPlatform are not directly saved in db
        em.detach(updatedAdCampaignPlatform);
        updatedAdCampaignPlatform
            .type(UPDATED_TYPE)
            .status(UPDATED_STATUS)
            .totalBudget(UPDATED_TOTAL_BUDGET)
            .remainingBudget(UPDATED_REMAINING_BUDGET)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE);

        restAdCampaignPlatformMockMvc.perform(put("/api/ad-campaign-platforms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdCampaignPlatform)))
            .andExpect(status().isOk());

        // Validate the AdCampaignPlatform in the database
        List<AdCampaignPlatform> adCampaignPlatformList = adCampaignPlatformRepository.findAll();
        assertThat(adCampaignPlatformList).hasSize(databaseSizeBeforeUpdate);
        AdCampaignPlatform testAdCampaignPlatform = adCampaignPlatformList.get(adCampaignPlatformList.size() - 1);
        assertThat(testAdCampaignPlatform.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAdCampaignPlatform.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAdCampaignPlatform.getTotalBudget()).isEqualTo(UPDATED_TOTAL_BUDGET);
        assertThat(testAdCampaignPlatform.getRemainingBudget()).isEqualTo(UPDATED_REMAINING_BUDGET);
        assertThat(testAdCampaignPlatform.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAdCampaignPlatform.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdCampaignPlatform() throws Exception {
        int databaseSizeBeforeUpdate = adCampaignPlatformRepository.findAll().size();

        // Create the AdCampaignPlatform

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdCampaignPlatformMockMvc.perform(put("/api/ad-campaign-platforms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adCampaignPlatform)))
            .andExpect(status().isBadRequest());

        // Validate the AdCampaignPlatform in the database
        List<AdCampaignPlatform> adCampaignPlatformList = adCampaignPlatformRepository.findAll();
        assertThat(adCampaignPlatformList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdCampaignPlatform() throws Exception {
        // Initialize the database
        adCampaignPlatformRepository.saveAndFlush(adCampaignPlatform);

        int databaseSizeBeforeDelete = adCampaignPlatformRepository.findAll().size();

        // Get the adCampaignPlatform
        restAdCampaignPlatformMockMvc.perform(delete("/api/ad-campaign-platforms/{id}", adCampaignPlatform.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdCampaignPlatform> adCampaignPlatformList = adCampaignPlatformRepository.findAll();
        assertThat(adCampaignPlatformList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdCampaignPlatform.class);
        AdCampaignPlatform adCampaignPlatform1 = new AdCampaignPlatform();
        adCampaignPlatform1.setId(1L);
        AdCampaignPlatform adCampaignPlatform2 = new AdCampaignPlatform();
        adCampaignPlatform2.setId(adCampaignPlatform1.getId());
        assertThat(adCampaignPlatform1).isEqualTo(adCampaignPlatform2);
        adCampaignPlatform2.setId(2L);
        assertThat(adCampaignPlatform1).isNotEqualTo(adCampaignPlatform2);
        adCampaignPlatform1.setId(null);
        assertThat(adCampaignPlatform1).isNotEqualTo(adCampaignPlatform2);
    }
}
