package model.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_MUSIC_LIST_CONTENT")
public class MusicListContentBean implements Serializable {
	@Id
	private Integer member_music_list_content_id;
	@Id
	private Integer member_music_list_content_music_id;

	public Integer getMember_music_list_content_id() {
		return member_music_list_content_id;
	}

	public void setMember_music_list_content_id(Integer member_music_list_content_id) {
		this.member_music_list_content_id = member_music_list_content_id;
	}

	public Integer getMember_music_list_content_music_id() {
		return member_music_list_content_music_id;
	}

	public void setMember_music_list_content_music_id(Integer member_music_list_content_music_id) {
		this.member_music_list_content_music_id = member_music_list_content_music_id;
	}

}
