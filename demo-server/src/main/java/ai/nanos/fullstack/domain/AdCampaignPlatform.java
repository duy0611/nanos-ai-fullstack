package ai.nanos.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import ai.nanos.fullstack.domain.enumeration.AdCampaignPlatformType;

import ai.nanos.fullstack.domain.enumeration.AdCampaignStatus;

/**
 * A AdCampaignPlatform.
 */
@Entity
@Table(name = "ad_campaign_platform")
public class AdCampaignPlatform implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "_type")
    private AdCampaignPlatformType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AdCampaignStatus status;

    @Column(name = "total_budget")
    private Integer totalBudget;

    @Column(name = "remaining_budget")
    private Integer remainingBudget;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Audiance targetAudiance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Creatives creatives;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Insights insights;

    @ManyToOne
    @JsonIgnoreProperties("platforms")
    private AdCampaign adCampaign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdCampaignPlatformType getType() {
        return type;
    }

    public AdCampaignPlatform type(AdCampaignPlatformType type) {
        this.type = type;
        return this;
    }

    public void setType(AdCampaignPlatformType type) {
        this.type = type;
    }

    public AdCampaignStatus getStatus() {
        return status;
    }

    public AdCampaignPlatform status(AdCampaignStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AdCampaignStatus status) {
        this.status = status;
    }

    public Integer getTotalBudget() {
        return totalBudget;
    }

    public AdCampaignPlatform totalBudget(Integer totalBudget) {
        this.totalBudget = totalBudget;
        return this;
    }

    public void setTotalBudget(Integer totalBudget) {
        this.totalBudget = totalBudget;
    }

    public Integer getRemainingBudget() {
        return remainingBudget;
    }

    public AdCampaignPlatform remainingBudget(Integer remainingBudget) {
        this.remainingBudget = remainingBudget;
        return this;
    }

    public void setRemainingBudget(Integer remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public AdCampaignPlatform startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public AdCampaignPlatform endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Audiance getTargetAudiance() {
        return targetAudiance;
    }

    public AdCampaignPlatform targetAudiance(Audiance audiance) {
        this.targetAudiance = audiance;
        return this;
    }

    public void setTargetAudiance(Audiance audiance) {
        this.targetAudiance = audiance;
    }

    public Creatives getCreatives() {
        return creatives;
    }

    public AdCampaignPlatform creatives(Creatives creatives) {
        this.creatives = creatives;
        return this;
    }

    public void setCreatives(Creatives creatives) {
        this.creatives = creatives;
    }

    public Insights getInsights() {
        return insights;
    }

    public AdCampaignPlatform insights(Insights insights) {
        this.insights = insights;
        return this;
    }

    public void setInsights(Insights insights) {
        this.insights = insights;
    }

    public AdCampaign getAdCampaign() {
        return adCampaign;
    }

    public AdCampaignPlatform adCampaign(AdCampaign adCampaign) {
        this.adCampaign = adCampaign;
        return this;
    }

    public void setAdCampaign(AdCampaign adCampaign) {
        this.adCampaign = adCampaign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdCampaignPlatform adCampaignPlatform = (AdCampaignPlatform) o;
        if (adCampaignPlatform.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adCampaignPlatform.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdCampaignPlatform{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            ", totalBudget=" + getTotalBudget() +
            ", remainingBudget=" + getRemainingBudget() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
