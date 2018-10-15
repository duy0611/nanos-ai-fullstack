package ai.nanos.fullstack.web.rest;

import ai.nanos.fullstack.DemoApp;

import ai.nanos.fullstack.domain.AdCampaign;
import ai.nanos.fullstack.repository.AdCampaignRepository;
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
import java.util.List;


import static ai.nanos.fullstack.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ai.nanos.fullstack.domain.enumeration.AdCampaignStatus;
/**
 * Test class for the AdCampaignResource REST controller.
 *
 * @see AdCampaignResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApp.class)
public class AdCampaignResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GOAL = "AAAAAAAAAA";
    private static final String UPDATED_GOAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_BUDGET = 1;
    private static final Integer UPDATED_TOTAL_BUDGET = 2;

    private static final AdCampaignStatus DEFAULT_STATUS = AdCampaignStatus.Scheduled;
    private static final AdCampaignStatus UPDATED_STATUS = AdCampaignStatus.Delivering;

    @Autowired
    private AdCampaignRepository adCampaignRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdCampaignMockMvc;

    private AdCampaign adCampaign;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdCampaignResource adCampaignResource = new AdCampaignResource(adCampaignRepository);
        this.restAdCampaignMockMvc = MockMvcBuilders.standaloneSetup(adCampaignResource)
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
    public static AdCampaign createEntity(EntityManager em) {
        AdCampaign adCampaign = new AdCampaign()
            .name(DEFAULT_NAME)
            .goal(DEFAULT_GOAL)
            .totalBudget(DEFAULT_TOTAL_BUDGET)
            .status(DEFAULT_STATUS);
        return adCampaign;
    }

    @Before
    public void initTest() {
        adCampaign = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdCampaign() throws Exception {
        int databaseSizeBeforeCreate = adCampaignRepository.findAll().size();

        // Create the AdCampaign
        restAdCampaignMockMvc.perform(post("/api/ad-campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adCampaign)))
            .andExpect(status().isCreated());

        // Validate the AdCampaign in the database
        List<AdCampaign> adCampaignList = adCampaignRepository.findAll();
        assertThat(adCampaignList).hasSize(databaseSizeBeforeCreate + 1);
        AdCampaign testAdCampaign = adCampaignList.get(adCampaignList.size() - 1);
        assertThat(testAdCampaign.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdCampaign.getGoal()).isEqualTo(DEFAULT_GOAL);
        assertThat(testAdCampaign.getTotalBudget()).isEqualTo(DEFAULT_TOTAL_BUDGET);
        assertThat(testAdCampaign.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAdCampaignWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adCampaignRepository.findAll().size();

        // Create the AdCampaign with an existing ID
        adCampaign.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdCampaignMockMvc.perform(post("/api/ad-campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adCampaign)))
            .andExpect(status().isBadRequest());

        // Validate the AdCampaign in the database
        List<AdCampaign> adCampaignList = adCampaignRepository.findAll();
        assertThat(adCampaignList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdCampaigns() throws Exception {
        // Initialize the database
        adCampaignRepository.saveAndFlush(adCampaign);

        // Get all the adCampaignList
        restAdCampaignMockMvc.perform(get("/api/ad-campaigns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adCampaign.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].goal").value(hasItem(DEFAULT_GOAL.toString())))
            .andExpect(jsonPath("$.[*].totalBudget").value(hasItem(DEFAULT_TOTAL_BUDGET)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAdCampaign() throws Exception {
        // Initialize the database
        adCampaignRepository.saveAndFlush(adCampaign);

        // Get the adCampaign
        restAdCampaignMockMvc.perform(get("/api/ad-campaigns/{id}", adCampaign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(adCampaign.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.goal").value(DEFAULT_GOAL.toString()))
            .andExpect(jsonPath("$.totalBudget").value(DEFAULT_TOTAL_BUDGET))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdCampaign() throws Exception {
        // Get the adCampaign
        restAdCampaignMockMvc.perform(get("/api/ad-campaigns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdCampaign() throws Exception {
        // Initialize the database
        adCampaignRepository.saveAndFlush(adCampaign);

        int databaseSizeBeforeUpdate = adCampaignRepository.findAll().size();

        // Update the adCampaign
        AdCampaign updatedAdCampaign = adCampaignRepository.findById(adCampaign.getId()).get();
        // Disconnect from session so that the updates on updatedAdCampaign are not directly saved in db
        em.detach(updatedAdCampaign);
        updatedAdCampaign
            .name(UPDATED_NAME)
            .goal(UPDATED_GOAL)
            .totalBudget(UPDATED_TOTAL_BUDGET)
            .status(UPDATED_STATUS);

        restAdCampaignMockMvc.perform(put("/api/ad-campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdCampaign)))
            .andExpect(status().isOk());

        // Validate the AdCampaign in the database
        List<AdCampaign> adCampaignList = adCampaignRepository.findAll();
        assertThat(adCampaignList).hasSize(databaseSizeBeforeUpdate);
        AdCampaign testAdCampaign = adCampaignList.get(adCampaignList.size() - 1);
        assertThat(testAdCampaign.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdCampaign.getGoal()).isEqualTo(UPDATED_GOAL);
        assertThat(testAdCampaign.getTotalBudget()).isEqualTo(UPDATED_TOTAL_BUDGET);
        assertThat(testAdCampaign.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAdCampaign() throws Exception {
        int databaseSizeBeforeUpdate = adCampaignRepository.findAll().size();

        // Create the AdCampaign

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdCampaignMockMvc.perform(put("/api/ad-campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(adCampaign)))
            .andExpect(status().isBadRequest());

        // Validate the AdCampaign in the database
        List<AdCampaign> adCampaignList = adCampaignRepository.findAll();
        assertThat(adCampaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdCampaign() throws Exception {
        // Initialize the database
        adCampaignRepository.saveAndFlush(adCampaign);

        int databaseSizeBeforeDelete = adCampaignRepository.findAll().size();

        // Get the adCampaign
        restAdCampaignMockMvc.perform(delete("/api/ad-campaigns/{id}", adCampaign.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AdCampaign> adCampaignList = adCampaignRepository.findAll();
        assertThat(adCampaignList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdCampaign.class);
        AdCampaign adCampaign1 = new AdCampaign();
        adCampaign1.setId(1L);
        AdCampaign adCampaign2 = new AdCampaign();
        adCampaign2.setId(adCampaign1.getId());
        assertThat(adCampaign1).isEqualTo(adCampaign2);
        adCampaign2.setId(2L);
        assertThat(adCampaign1).isNotEqualTo(adCampaign2);
        adCampaign1.setId(null);
        assertThat(adCampaign1).isNotEqualTo(adCampaign2);
    }
}
