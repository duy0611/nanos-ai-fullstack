package ai.nanos.fullstack.domain;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * A Audiance.
 */
@Entity
@Table(name = "audiance")
public class Audiance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "genders")
    private String genders;

    @Column(name = "age_range")
    private String ageRange;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "audiance_language_code", joinColumns = @JoinColumn(name = "audiance_id"))
    @Column(name = "language_code")
    private Set<String> languages = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "audiance_location", joinColumns = @JoinColumn(name = "audiance_id"))
    @Column(name = "location")
    private Set<String> locations = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "audiance_interest", joinColumns = @JoinColumn(name = "audiance_id"))
    @Column(name = "interest")
    private Set<String> interests = new HashSet<>();
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "audiance_key_word", joinColumns = @JoinColumn(name = "audiance_id"))
    @Column(name = "key_word")
    private Set<String> keyWords = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenders() {
        return genders;
    }

    public Audiance genders(String genders) {
        this.genders = genders;
        return this;
    }

    public void setGenders(String genders) {
        this.genders = genders;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public Audiance ageRange(String ageRange) {
        this.ageRange = ageRange;
        return this;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public Audiance languages(Set<String> languages) {
        this.languages = languages;
        return this;
    }

    public Audiance addLanguages(String language) {
        this.languages.add(language);
        return this;
    }

    public Audiance removeLanguages(String language) {
        this.languages.remove(language);
        return this;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public Set<String> getLocations() {
        return locations;
    }

    public Audiance locations(Set<String> locations) {
        this.locations = locations;
        return this;
    }

    public Audiance addLocations(String location) {
        this.locations.add(location);
        return this;
    }

    public Audiance removeLocations(String location) {
        this.locations.remove(location);
        return this;
    }

    public void setLocations(Set<String> locations) {
        this.locations = locations;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public Audiance interests(Set<String> interests) {
        this.interests = interests;
        return this;
    }

    public Audiance addInterests(String interest) {
        this.interests.add(interest);
        return this;
    }

    public Audiance removeInterests(String interest) {
        this.interests.remove(interest);
        return this;
    }

    public void setInterests(Set<String> interests) {
        this.interests = interests;
    }
    
    public Set<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(Set<String> keyWords) {
		this.keyWords = keyWords;
	}
	
	public Audiance addKeyWords(String keyWord) {
        this.keyWords.add(keyWord);
        return this;
    }

    public Audiance removeKeyWords(String keyWord) {
        this.keyWords.remove(keyWord);
        return this;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Audiance audiance = (Audiance) o;
        if (audiance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), audiance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
	public String toString() {
		return "Audiance [id=" + id + ", genders=" + genders + ", ageRange=" + ageRange + ", languages=" + languages
				+ ", locations=" + locations + ", interests=" + interests + ", keyWords=" + keyWords + "]";
	}
}
