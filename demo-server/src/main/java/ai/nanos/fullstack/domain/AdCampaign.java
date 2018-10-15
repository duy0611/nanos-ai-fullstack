package ai.nanos.fullstack.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ai.nanos.fullstack.domain.enumeration.AdCampaignStatus;

/**
 * A AdCampaign.
 */
@Entity
@Table(name = "ad_campaign")
public class AdCampaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "goal")
    private String goal;

    @Column(name = "total_budget")
    private Integer totalBudget;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AdCampaignStatus status;

    @OneToMany(mappedBy = "adCampaign")
    private Set<AdCampaignPlatform> platforms = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AdCampaign name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public AdCampaign goal(String goal) {
        this.goal = goal;
        return this;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Integer getTotalBudget() {
        return totalBudget;
    }

    public AdCampaign totalBudget(Integer totalBudget) {
        this.totalBudget = totalBudget;
        return this;
    }

    public void setTotalBudget(Integer totalBudget) {
        this.totalBudget = totalBudget;
    }

    public AdCampaignStatus getStatus() {
        return status;
    }

    public AdCampaign status(AdCampaignStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AdCampaignStatus status) {
        this.status = status;
    }

    public Set<AdCampaignPlatform> getPlatforms() {
        return platforms;
    }

    public AdCampaign platforms(Set<AdCampaignPlatform> adCampaignPlatforms) {
        this.platforms = adCampaignPlatforms;
        return this;
    }

    public AdCampaign addPlatforms(AdCampaignPlatform adCampaignPlatform) {
        this.platforms.add(adCampaignPlatform);
        adCampaignPlatform.setAdCampaign(this);
        return this;
    }

    public AdCampaign removePlatforms(AdCampaignPlatform adCampaignPlatform) {
        this.platforms.remove(adCampaignPlatform);
        adCampaignPlatform.setAdCampaign(null);
        return this;
    }

    public void setPlatforms(Set<AdCampaignPlatform> adCampaignPlatforms) {
        this.platforms = adCampaignPlatforms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdCampaign adCampaign = (AdCampaign) o;
        if (adCampaign.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adCampaign.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdCampaign{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", goal='" + getGoal() + "'" +
            ", totalBudget=" + getTotalBudget() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
