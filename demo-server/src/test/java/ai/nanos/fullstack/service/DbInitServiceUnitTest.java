package ai.nanos.fullstack.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import ai.nanos.fullstack.domain.AdCampaign;
import ai.nanos.fullstack.repository.AdCampaignPlatformRepository;
import ai.nanos.fullstack.repository.AdCampaignRepository;

public class DbInitServiceUnitTest {
	
	private DbInitService dbInitService;
	
	@MockBean
	private AdCampaignRepository adCampaignRepo;
	
	@MockBean
	private AdCampaignPlatformRepository adCampaignPlatformRepo;
	
	@Before
    public void setup() {
		dbInitService = new DbInitService();
		ReflectionTestUtils.setField(dbInitService, "adCampaignRepo", adCampaignRepo);
		ReflectionTestUtils.setField(dbInitService, "adCampaignPlatformRepo", adCampaignPlatformRepo);
	}

	@Test
	public void parseFromJsonTest() {
		List<AdCampaign> resultList = dbInitService.parseFromJson();
		
		assertThat(resultList.size() == 3);
		assertThat(resultList.get(0).getName().equals("Test Ad 1"));
	}
}
