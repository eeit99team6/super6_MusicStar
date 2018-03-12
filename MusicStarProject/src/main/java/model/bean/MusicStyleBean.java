package model.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUSIC_STYLE")
public class MusicStyleBean {
	@Id
	private Integer music_style_id;
	private String music_style_name;

	public Integer getMusic_style_id() {
		return music_style_id;
	}

	public void setMusic_style_id(Integer music_style_id) {
		this.music_style_id = music_style_id;
	}

	public String getMusic_style_name() {
		return music_style_name;
	}

	public void setMusic_style_name(String music_style_name) {
		this.music_style_name = music_style_name;
	}

}
