package model;

import java.io.Serializable;

public class Piece implements Serializable{
	private static final long serialVersionUID =1L;
	private int opp_id;
	private int piece_id;
	private int x_cordinate;
	private int y_cordinate;
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
	 * @return the piece_id
	 */
	public int getPiece_id() {
		return piece_id;
	}
	/**
	 * @param piece_id the piece_id to set
	 */
	public void setPiece_id(int piece_id) {
		this.piece_id = piece_id;
	}
	/**
	 * @return the x_cordinate
	 */
	public int getX_cordinate() {
		return x_cordinate;
	}
	/**
	 * @param x_cordinate the x_cordinate to set
	 */
	public void setX_cordinate(int x_cordinate) {
		this.x_cordinate = x_cordinate;
	}
	/**
	 * @return the y_cordinate
	 */
	public int getY_cordinate() {
		return y_cordinate;
	}
	/**
	 * @param y_cordinate the y_cordinate to set
	 */
	public void setY_cordinate(int y_cordinate) {
		this.y_cordinate = y_cordinate;
	}
	/**
	 * @return the con_time
	 */
	public java.sql.Timestamp getCon_time() {
		return con_time;
	}
	/**
	 * @param con_time the con_time to set
	 */
	public void setCon_time(java.sql.Timestamp con_time) {
		this.con_time = con_time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Piece [opp_id=" + opp_id + ", piece_id=" + piece_id + ", x_cordinate=" + x_cordinate + ", y_cordinate="
				+ y_cordinate + ", con_time=" + con_time + "]";
	}
	
	
}
