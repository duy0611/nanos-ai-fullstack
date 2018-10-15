package ai.nanos.fullstack.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Creatives.
 */
@Entity
@Table(name = "creatives")
public class Creatives implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "text_header")
    private String header;
    
    @Column(name = "text_header1")
    private String header1;
    
    @Column(name = "text_header2")
    private String header2;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "image")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public Creatives header(String header) {
        this.header = header;
        return this;
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

    public Creatives description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public Creatives url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public Creatives image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Creatives creatives = (Creatives) o;
        if (creatives.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), creatives.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
	public String toString() {
		return "Creatives [id=" + id + ", header=" + header + ", header1=" + header1 + ", header2=" + header2
				+ ", description=" + description + ", url=" + url + ", image=" + image + "]";
	}
}
