package model.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "VOTES")
public class MusicContestVoteBean implements Serializable {
	@Id
	private Integer music_contest_id;
	@Id
	private String voter_id;
	private String music_contest_player_id;

	public Integer getMusic_contest_id() {
		return music_contest_id;
	}

	public void setMusic_contest_id(Integer music_contest_id) {
		this.music_contest_id = music_contest_id;
	}

	public String getVoter_id() {
		return voter_id;
	}

	public void setVoter_id(String voter_id) {
		this.voter_id = voter_id;
	}

	public String getMusic_contest_player_id() {
		return music_contest_player_id;
	}

	public void setMusic_contest_player_id(String music_contest_player_id) {
		this.music_contest_player_id = music_contest_player_id;
	}

	public MusicContestVoteBean(Integer music_contest_id, String voter_id, String music_contest_player_id) {
		this.music_contest_id = music_contest_id;
		this.voter_id = voter_id;
		this.music_contest_player_id = music_contest_player_id;
	}
	public MusicContestVoteBean(Integer music_contest_id, String voter_id) {
		this.music_contest_id = music_contest_id;
		this.voter_id = voter_id;
	}
	public MusicContestVoteBean() {
	}

}
