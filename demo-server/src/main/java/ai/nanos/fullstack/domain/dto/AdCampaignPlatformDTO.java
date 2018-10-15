package ai.nanos.fullstack.domain.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import ai.nanos.fullstack.domain.AdCampaignPlatform;
import ai.nanos.fullstack.domain.enumeration.AdCampaignStatus;

public class AdCampaignPlatformDTO {
	
	private Long id;

	private String status;
	@JsonProperty("total_budget")
	private Integer totalBudget;
	@JsonProperty("remaining_budget")
	private Integer remainingBudget;
	@JsonProperty("start_date")
	private Long startDate;
	@JsonProperty("end_date")
	private Long endDate;
	
	@JsonProperty("target_audiance")
	private AudianceDTO targetAudiance;
	
	private CreativesDTO creatives;
	private InsightsDTO insights;
	
	public AdCampaignPlatformDTO() { }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(Integer totalBudget) {
		this.totalBudget = totalBudget;
	}

	public Integer getRemainingBudget() {
		return remainingBudget;
	}

	public void setRemainingBudget(Integer remainingBudget) {
		this.remainingBudget = remainingBudget;
	}
	
	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public AudianceDTO getTargetAudiance() {
		return targetAudiance;
	}

	public void setTargetAudiance(AudianceDTO targetAudiance) {
		this.targetAudiance = targetAudiance;
	}

	public CreativesDTO getCreatives() {
		return creatives;
	}

	public void setCreatives(CreativesDTO creatives) {
		this.creatives = creatives;
	}

	public InsightsDTO getInsights() {
		return insights;
	}

	public void setInsights(InsightsDTO insights) {
		this.insights = insights;
	}

	@Override
	public String toString() {
		return "AdCampaignPlatformDTO [status=" + status + ", totalBudget=" + totalBudget + ", remainingBudget="
				+ remainingBudget + ", startDate=" + startDate + ", endDate=" + endDate + ", targetAudiance="
				+ targetAudiance + ", creatives=" + creatives + ", insights=" + insights + "]";
	}

	public AdCampaignPlatform convertToCampaignPlatform() {
		AdCampaignPlatform result = new AdCampaignPlatform();
		result.setId(getId());
		result.setStatus(AdCampaignStatus.of(getStatus()));
		result.setTotalBudget(getTotalBudget());
		result.setRemainingBudget(getRemainingBudget());
		result.setStartDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli(getStartDate()), ZoneId.of("UTC")));
		result.setEndDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli(getEndDate()), ZoneId.of("UTC")));
		
		result.setTargetAudiance(targetAudiance.convertToAudiance());
		result.setCreatives(creatives.convertToCreatives());
		result.setInsights(insights.convertToInsights());
		
		return result;
	}
	
}
