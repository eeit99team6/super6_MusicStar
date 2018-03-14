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
	private String slide_link;
	private String slide_description;
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
	public String getSlide_link() {
		return slide_link;
	}
	public void setSlide_link(String slide_link) {
		this.slide_link = slide_link;
	}
	public String getSlide_description() {
		return slide_description;
	}
	public void setSlide_description(String slide_description) {
		this.slide_description = slide_description;
	}
	public Short getSlide_order() {
		return slide_order;
	}
	public void setSlide_order(Short slide_order) {
		this.slide_order = slide_order;
	}

}
