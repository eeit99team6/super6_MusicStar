package model.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_MUSIC_LIST")
public class MusicListBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer member_music_list_id;
	private String member_music_list_name;
	private String member_music_list_description;
	private String member_music_list_member_id;
	private Short member_music_list_quantity;

	public Integer getMember_music_list_id() {
		return member_music_list_id;
	}

	public void setMember_music_list_id(Integer member_music_list_id) {
		this.member_music_list_id = member_music_list_id;
	}

	public String getMember_music_list_name() {
		return member_music_list_name;
	}

	public void setMember_music_list_name(String member_music_list_name) {
		this.member_music_list_name = member_music_list_name;
	}

	public String getMember_music_list_description() {
		return member_music_list_description;
	}

	public void setMember_music_list_description(String member_music_list_description) {
		this.member_music_list_description = member_music_list_description;
	}

	public Short getMember_music_list_quantity() {
		return member_music_list_quantity;
	}

	public void setMember_music_list_quantity(Short member_music_list_quantity) {
		this.member_music_list_quantity = member_music_list_quantity;
	}

	public String getMember_music_list_member_id() {
		return member_music_list_member_id;
	}

	public void setMember_music_list_member_id(String member_music_list_member_id) {
		this.member_music_list_member_id = member_music_list_member_id;
	}

}
