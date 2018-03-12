package model.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SLIDES")
public class SlideshowBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer slide_id;
	private String slide_name;
	private String slide_photo;
	private String slid_link;
	private String slid_description;
	private Short slide_order;

	public Integer getSlide_id() {
		return slide_id;
	}

	public void setSlide_id(Integer slide_id) {
		this.slide_id = slide_id;
	}

	public String getSlide_name() {
		return slide_name;
	}

	public void setSlide_name(String slide_name) {
		this.slide_name = slide_name;
	}

	public String getSlide_photo() {
		return slide_photo;
	}

	public void setSlide_photo(String slide_photo) {
		this.slide_photo = slide_photo;
	}

	public String getSlid_link() {
		return slid_link;
	}

	public void setSlid_link(String slid_link) {
		this.slid_link = slid_link;
	}

	public String getSlid_description() {
		return slid_description;
	}

	public void setSlid_description(String slid_description) {
		this.slid_description = slid_description;
	}

	public Short getSlide_order() {
		return slide_order;
	}

	public void setSlide_order(Short slide_order) {
		this.slide_order = slide_order;
	}

}
