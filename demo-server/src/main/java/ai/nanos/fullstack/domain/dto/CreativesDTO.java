package ai.nanos.fullstack.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import ai.nanos.fullstack.domain.Creatives;

public class CreativesDTO {

	private String header;
	
	@JsonProperty("header_1")
	private String header1;
	
	@JsonProperty("header_2")
	private String header2;
	
	private String description;
	private String url;
	private String image;
	
	public CreativesDTO() { }

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getHeader1() {
		return header1;
	}

	public void setHeader1(String header1) {
		this.header1 = header1;
	}

	public String getHeader2() {
		return header2;
	}

	public void setHeader2(String header2) {
		this.header2 = header2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CreativesDTO [header=" + header + ", header1=" + header1 + ", header2=" + header2 + ", description="
				+ description + ", url=" + url + ", image=" + image + "]";
	}

	public Creatives convertToCreatives() {
		Creatives result = new Creatives();
		result.setHeader(getHeader());
		result.setHeader1(getHeader1());
		result.setHeader2(getHeader2());
		result.setDescription(getDescription());
		result.setUrl(getUrl());
		result.setImage(getImage());
		
		return result;
	}
	
}
