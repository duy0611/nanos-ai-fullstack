package ai.nanos.fullstack.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ai.nanos.fullstack.domain.Audiance;

public class AudianceDTO {

	private List<String> languages = new ArrayList<>();
	
	private List<String> genders = new ArrayList<>();
	
	@JsonProperty("age_range")
	private List<String> ageRange = new ArrayList<>();
	
	private List<String> locations = new ArrayList<>();
	
	private List<String> interests = new ArrayList<>();
	
	@JsonProperty("KeyWords")
	private List<String> keyWords = new ArrayList<>();
	
	public AudianceDTO() { }

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getGenders() {
		return genders;
	}

	public void setGenders(List<String> genders) {
		this.genders = genders;
	}

	public List<String> getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(List<String> ageRange) {
		this.ageRange = ageRange;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		return "AudianceDTO [languages=" + languages + ", genders=" + genders + ", ageRange=" + ageRange
				+ ", locations=" + locations + ", interests=" + interests + ", keyWords=" + keyWords + "]";
	}

	public Audiance convertToAudiance() {
		Audiance result = new Audiance();
		
		Collections.sort(genders);
		Collections.sort(ageRange);
		
		result.setGenders(String.join(",", genders));
		result.setAgeRange(String.join("-", ageRange));
		
		result.setLanguages(new HashSet<>(languages));
		result.setLocations(new HashSet<>(locations));
		result.setInterests(new HashSet<>(interests));
		result.setKeyWords(new HashSet<>(keyWords));
		
		return result;
	}
	
}
