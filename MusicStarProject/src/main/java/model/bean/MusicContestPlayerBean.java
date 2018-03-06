package model.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "MUSIC_CONTEST_PLAYERS")
public class MusicContestPlayerBean implements Serializable {
	@Id
	private Integer music_contest_id;
	@Id
	private String music_contest_player_id;
	private Integer music_id;
	private Integer music_contest_players_votes;

	public Integer getMusic_contest_id() {
		return music_contest_id;
	}

	public void setMusic_contest_id(Integer music_contest_id) {
		this.music_contest_id = music_contest_id;
	}

	public String getMusic_contest_player_id() {
		return music_contest_player_id;
	}

	public void setMusic_contest_player_id(String music_contest_player_id) {
		this.music_contest_player_id = music_contest_player_id;
	}

	public Integer getMusic_id() {
		return music_id;
	}

	public void setMusic_id(Integer music_id) {
		this.music_id = music_id;
	}

	public Integer getMusic_contest_players_votes() {
		return music_contest_players_votes;
	}

	public void setMusic_contest_players_votes(Integer music_contest_players_votes) {
		this.music_contest_players_votes = music_contest_players_votes;
	}

}
