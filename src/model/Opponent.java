package model;

import java.sql.Timestamp;

public class Opponent {
	private int opp_id;
	int ID_black;
	private int ID_white;
	private int winner;
	private java.sql.Timestamp con_time;
	/**
	 * @return the opp_id
	 */
	public int getOpp_id() {
		return opp_id;
	}
	/**
	 * @param opp_id the opp_id to set
	 */
	public void setOpp_id(int opp_id) {
		this.opp_id = opp_id;
	}
	/**
	 * @return the iD_black
	 */
	public int getID_black() {
		return ID_black;
	}
	/**
	 * @param iD_black the iD_black to set
	 */
	public void setID_black(int iD_black) {
		ID_black = iD_black;
	}
	/**
	 * @return the iD_white
	 */
	public int getID_white() {
		return ID_white;
	}
	/**
	 * @param iD_white the iD_white to set
	 */
	public void setID_white(int iD_white) {
		ID_white = iD_white;
	}
	/**
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}
	/**
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}
	/**
	 * @return the con_time
	 */
	public Timestamp getCon_time() {
		return con_time;
	}
	/**
	 * @param con_time the con_time to set
	 */
	public void setCon_time(Timestamp con_time) {
		this.con_time = con_time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Opponent [opp_id=" + opp_id + ", ID_black=" + ID_black + ", ID_white=" + ID_white + ", winner=" + winner
				+ ", con_time=" + con_time + "]";
	}

}
