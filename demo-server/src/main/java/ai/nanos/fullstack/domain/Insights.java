package ai.nanos.fullstack.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Insights.
 */
@Entity
@Table(name = "insights")
public class Insights implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "impression")
    private Integer impression;

    @Column(name = "clicks")
    private Integer clicks;
    
    @Column(name = "website_visits")
    private Integer websiteVisits;

    @Column(name = "nanos_score")
    private Double nanosScore;

    @Column(name = "cost_per_click")
    private Double costPerClick;

    @Column(name = "click_through_rate")
    private Double clickThroughRate;

    @Column(name = "advanced_kpi_1")
    private Double advancedKpi1;

    @Column(name = "advanced_kpi_2")
    private Double advancedKpi2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getImpression() {
        return impression;
    }

    public Insights impression(Integer impression) {
        this.impression = impression;
        return this;
    }

    public void setImpression(Integer impression) {
        this.impression = impression;
    }

    public Integer getClicks() {
        return clicks;
    }

    public Insights clicks(Integer clicks) {
        this.clicks = clicks;
        return this;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
    
    public Integer getWebsiteVisits() {
		return websiteVisits;
	}
    
    public Insights websiteVisits(Integer websiteVisits) {
        this.websiteVisits = websiteVisits;
        return this;
    }
    
	public void setWebsiteVisits(Integer websiteVisits) {
		this.websiteVisits = websiteVisits;
	}

	public Double getNanosScore() {
        return nanosScore;
    }

    public Insights nanosScore(Double nanosScore) {
        this.nanosScore = nanosScore;
        return this;
    }

    public void setNanosScore(Double nanosScore) {
        this.nanosScore = nanosScore;
    }

    public Double getCostPerClick() {
        return costPerClick;
    }

    public Insights costPerClick(Double costPerClick) {
        this.costPerClick = costPerClick;
        return this;
    }

    public void setCostPerClick(Double costPerClick) {
        this.costPerClick = costPerClick;
    }

    public Double getClickThroughRate() {
        return clickThroughRate;
    }

    public Insights clickThroughRate(Double clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
        return this;
    }

    public void setClickThroughRate(Double clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
    }

    public Double getAdvancedKpi1() {
        return advancedKpi1;
    }

    public Insights advancedKpi1(Double advancedKpi1) {
        this.advancedKpi1 = advancedKpi1;
        return this;
    }

    public void setAdvancedKpi1(Double advancedKpi1) {
        this.advancedKpi1 = advancedKpi1;
    }

    public Double getAdvancedKpi2() {
        return advancedKpi2;
    }

    public Insights advancedKpi2(Double advancedKpi2) {
        this.advancedKpi2 = advancedKpi2;
        return this;
    }

    public void setAdvancedKpi2(Double advancedKpi2) {
        this.advancedKpi2 = advancedKpi2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Insights insights = (Insights) o;
        if (insights.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insights.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
	public String toString() {
		return "Insights [id=" + id + ", impression=" + impression + ", clicks=" + clicks + ", websiteVisits="
				+ websiteVisits + ", nanosScore=" + nanosScore + ", costPerClick=" + costPerClick
				+ ", clickThroughRate=" + clickThroughRate + ", advancedKpi1=" + advancedKpi1 + ", advancedKpi2="
				+ advancedKpi2 + "]";
	}
}
