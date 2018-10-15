package ai.nanos.fullstack.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import ai.nanos.fullstack.domain.Insights;

public class InsightsDTO {

	private Integer impressions;
	private Integer clicks;
	@JsonProperty("website_visits")
	private Integer websiteVisits;
	@JsonProperty("nanos_score")
	private Double nanosScore;
	@JsonProperty("cost_per_click")
	private Double costPerCLick;
	@JsonProperty("click_through_rate")
	private Double clickThroughRate;
	@JsonProperty("advanced_kpi_1")
	private Double advancedKpi1;
	@JsonProperty("advanced_kpi_2")
	private Double advancedKpi2;
	
	public InsightsDTO() { }

	public Integer getImpressions() {
		return impressions;
	}

	public void setImpressions(Integer impressions) {
		this.impressions = impressions;
	}

	public Integer getClicks() {
		return clicks;
	}

	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	
	public Integer getWebsiteVisits() {
		return websiteVisits;
	}

	public void setWebsiteVisits(Integer websiteVisits) {
		this.websiteVisits = websiteVisits;
	}

	public Double getNanosScore() {
		return nanosScore;
	}

	public void setNanosScore(Double nanosScore) {
		this.nanosScore = nanosScore;
	}

	public Double getCostPerCLick() {
		return costPerCLick;
	}

	public void setCostPerCLick(Double costPerCLick) {
		this.costPerCLick = costPerCLick;
	}

	public Double getClickThroughRate() {
		return clickThroughRate;
	}

	public void setClickThroughRate(Double clickThroughRate) {
		this.clickThroughRate = clickThroughRate;
	}

	public Double getAdvancedKpi1() {
		return advancedKpi1;
	}

	public void setAdvancedKpi1(Double advancedKpi1) {
		this.advancedKpi1 = advancedKpi1;
	}

	public Double getAdvancedKpi2() {
		return advancedKpi2;
	}

	public void setAdvancedKpi2(Double advancedKpi2) {
		this.advancedKpi2 = advancedKpi2;
	}

	@Override
	public String toString() {
		return "InsightsDTO [impressions=" + impressions + ", clicks=" + clicks + ", websiteVisits=" + websiteVisits
				+ ", nanosScore=" + nanosScore + ", costPerCLick=" + costPerCLick + ", clickThroughRate="
				+ clickThroughRate + ", advancedKpi1=" + advancedKpi1 + ", advancedKpi2=" + advancedKpi2 + "]";
	}

	public Insights convertToInsights() {
		Insights result = new Insights();
		result.setImpression(getImpressions());
		result.setClicks(getClicks());
		result.setWebsiteVisits(getWebsiteVisits());
		result.setNanosScore(getNanosScore());
		result.setClickThroughRate(getClickThroughRate());
		result.setCostPerClick(getCostPerCLick());
		result.setAdvancedKpi1(getAdvancedKpi1());
		result.setAdvancedKpi2(getAdvancedKpi2());
		
		return result;
	}
	
}
