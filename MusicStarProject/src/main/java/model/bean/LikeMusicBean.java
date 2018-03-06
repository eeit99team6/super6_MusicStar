package model.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "LIKES")
public class LikeMusicBean implements Serializable {
	@Id
	String likes_member_id;
	@Id
	Integer likes_music_id;

	public String getLikes_member_id() {
		return likes_member_id;
	}

	public void setLikes_member_id(String likes_member_id) {
		this.likes_member_id = likes_member_id;
	}

	public Integer getLikes_music_id() {
		return likes_music_id;
	}

	public void setLikes_music_id(Integer likes_music_id) {
		this.likes_music_id = likes_music_id;
	}

}
