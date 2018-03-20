package model.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUSIC")
public class MusicBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer music_id;
	private Integer music_style_id;
	private String music_name;
	private String music_link;
	private String music_member_id;
	private String music_photo;
	private String music_description;
	private String music_lyrics;
	private String music_likes;

	public Integer getMusic_id() {
		return music_id;
	}

	public void setMusic_id(Integer music_id) {
		this.music_id = music_id;
	}

	public Integer getMusic_style_id() {
		return music_style_id;
	}

	public void setMusic_style_id(Integer music_style_id) {
		this.music_style_id = music_style_id;
	}

	public String getMusic_name() {
		return music_name;
	}

	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}

	public String getMusic_link() {
		return music_link;
	}

	public void setMusic_link(String music_link) {
		this.music_link = music_link;
	}

	public String getMusic_member_id() {
		return music_member_id;
	}

	public void setMusic_member_id(String music_member_id) {
		this.music_member_id = music_member_id;
	}

	public String getMusic_photo() {
		return music_photo;
	}

	public void setMusic_photo(String music_photo) {
		this.music_photo = music_photo;
	}

	public String getMusic_description() {
		return music_description;
	}

	public void setMusic_description(String music_description) {
		this.music_description = music_description;
	}

	public String getMusic_lyrics() {
		return music_lyrics;
	}

	public void setMusic_lyrics(String music_lyrics) {
		this.music_lyrics = music_lyrics;
	}

	public String getMusic_likes() {
		return music_likes;
	}

	public void setMusic_likes(String music_likes) {
		this.music_likes = music_likes;
	}

}
