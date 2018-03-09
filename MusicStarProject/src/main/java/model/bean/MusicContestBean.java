package model.bean;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="music_contest")
public class MusicContestBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer music_contest_id;
	private String music_contest_name;
	private String music_contest_description;
	private String music_contest_photo;
	private Integer music_contest_style_id;
	private String music_contest_status;
	private java.util.Date music_contest_apply_start_date;
	private java.util.Date music_contest_validate_date;
	private java.util.Date music_contest_vote_start_date;
	private java.util.Date music_contest_end_date;
	
	
	@Override
	public String toString() {
		return "MusicContestBean [music_contest_id=" + music_contest_id + ", music_contest_name=" + music_contest_name
				+ ", music_contest_description=" + music_contest_description + ", music_contest_photo="
				+ music_contest_photo + ", music_contest_style_id=" + music_contest_style_id + ", music_contest_status="
				+ music_contest_status + ", music_contest_apply_start_date=" + music_contest_apply_start_date
				+ ", music_contest_validate_date=" + music_contest_validate_date + ", music_contest_vote_start_date="
				+ music_contest_vote_start_date + ", music_contest_end_date=" + music_contest_end_date + "]";
	}
	
	public Integer getMusic_contest_id() {
		return music_contest_id;
	}
	public void setMusic_contest_id(Integer music_contest_id) {
		this.music_contest_id = music_contest_id;
	}
	public String getMusic_contest_name() {
		return music_contest_name;
	}
	public void setMusic_contest_name(String music_contest_name) {
		this.music_contest_name = music_contest_name;
	}
	public Integer getMusic_contest_style_id() {
		return music_contest_style_id;
	}
	public void setMusic_contest_style_id(Integer music_contest_style_id) {
		this.music_contest_style_id = music_contest_style_id;
	}
	public String getMusic_contest_status() {
		return music_contest_status;
	}
	public void setMusic_contest_status(String music_contest_status) {
		this.music_contest_status = music_contest_status;
	}
	public java.util.Date getMusic_contest_apply_start_date() {
		return music_contest_apply_start_date;
	}
	public void setMusic_contest_apply_start_date(java.util.Date music_contest_apply_start_date) {
		this.music_contest_apply_start_date = music_contest_apply_start_date;
	}
	public java.util.Date getMusic_contest_validate_date() {
		return music_contest_validate_date;
	}
	public void setMusic_contest_validate_date(java.util.Date music_contest_validate_date) {
		this.music_contest_validate_date = music_contest_validate_date;
	}
	public java.util.Date getMusic_contest_vote_start_date() {
		return music_contest_vote_start_date;
	}
	public void setMusic_contest_vote_start_date(java.util.Date music_contest_vote_start_date) {
		this.music_contest_vote_start_date = music_contest_vote_start_date;
	}
	public java.util.Date getMusic_contest_end_date() {
		return music_contest_end_date;
	}
	public void setMusic_contest_end_date(java.util.Date music_contest_end_date) {
		this.music_contest_end_date = music_contest_end_date;
	}

	public String getMusic_contest_description() {
		return music_contest_description;
	}

	public void setMusic_contest_description(String music_contest_description) {
		this.music_contest_description = music_contest_description;
	}

	public String getMusic_contest_photo() {
		return music_contest_photo;
	}

	public void setMusic_contest_photo(String music_contest_photo) {
		this.music_contest_photo = music_contest_photo;
	}

	
	
}
