package ai.nanos.fullstack.domain.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonProperty;

import ai.nanos.fullstack.domain.AdCampaign;
import ai.nanos.fullstack.domain.AdCampaignPlatform;
import ai.nanos.fullstack.domain.enumeration.AdCampaignPlatformType;
import ai.nanos.fullstack.domain.enumeration.AdCampaignStatus;

public class AdCampaignDTO {

	private Long id;
	
	private String name;
	private String goal;
	@JsonProperty("total_budget")
	private Integer totalBudget;
	private String status;
	
	private Map<String, AdCampaignPlatformDTO> platforms = new HashMap<>();
	
	public AdCampaignDTO() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public Integer getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(Integer totalBudget) {
		this.totalBudget = totalBudget;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<String, AdCampaignPlatformDTO> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(Map<String, AdCampaignPlatformDTO> platforms) {
		this.platforms = platforms;
	}

	@Override
	public String toString() {
		return "AdCampaignDTO [id=" + id + ", name=" + name + ", goal=" + goal + ", totalBudget=" + totalBudget
				+ ", status=" + status + ", platforms=" + platforms + "]";
	}
	
	public AdCampaign convertToAdCampaign() {
		AdCampaign result = new AdCampaign();
		result.setId(getId());
		result.setName(getName());
		result.setGoal(getGoal());
		result.setTotalBudget(getTotalBudget());	
		result.setStatus(AdCampaignStatus.of(getStatus()));
		
		for (Entry<String, AdCampaignPlatformDTO> entry : platforms.entrySet()) {
			AdCampaignPlatform platform = entry.getValue().convertToCampaignPlatform();
			platform.setType(AdCampaignPlatformType.of(entry.getKey()));
			
			platform.setAdCampaign(result);
			result.addPlatforms(platform);
		}
		
		return result;
	}
}
