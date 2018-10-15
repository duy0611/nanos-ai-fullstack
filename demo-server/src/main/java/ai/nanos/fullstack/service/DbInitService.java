package ai.nanos.fullstack.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ai.nanos.fullstack.domain.AdCampaign;
import ai.nanos.fullstack.domain.AdCampaignPlatform;
import ai.nanos.fullstack.domain.dto.AdCampaignDTO;
import ai.nanos.fullstack.repository.AdCampaignPlatformRepository;
import ai.nanos.fullstack.repository.AdCampaignRepository;

@Service
public class DbInitService {
	
	private final Logger log = LoggerFactory.getLogger(DbInitService.class);
	
	private static String JSON_PATH = "/static/json/db.json";
	
	@Autowired
	private AdCampaignRepository adCampaignRepo;
	
	@Autowired
	private AdCampaignPlatformRepository adCampaignPlatformRepo;
	
	@Transactional
	public void initDB() {
		List<AdCampaign> resultList = parseFromJson();
		if (resultList != null && resultList.size() > 0) {
			
			for (AdCampaign ad : resultList) {
				ad.setId(null); //remove id from json file if any
				adCampaignRepo.save(ad);
				
				for (AdCampaignPlatform adPlatform : ad.getPlatforms()) {
					adPlatform.setId(null); //remove id from json file if any
					adCampaignPlatformRepo.save(adPlatform);				
				}			
			}
		}
	}

	public List<AdCampaign> parseFromJson() {
		// read json and write to db
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.registerModule(new Jdk8Module());
		
		TypeReference<List<AdCampaignDTO>> typeReference = new TypeReference<List<AdCampaignDTO>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream(JSON_PATH);
		
		try {
			List<AdCampaignDTO> adCampaigns = mapper.readValue(inputStream, typeReference);
			log.debug(adCampaigns.toString());
			
			return adCampaigns.stream().map(AdCampaignDTO::convertToAdCampaign).collect(Collectors.toList());
		}
		catch (IOException e) {
			throw new RuntimeException("Error when parsing and saving json: " + e.getMessage());
		}
	}
	
}
