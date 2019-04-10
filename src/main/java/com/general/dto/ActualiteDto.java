package com.general.dto;

import java.util.Date;

import org.jtransfo.MappedBy;

import com.general.model.User;

public class ActualiteDto {
	
	@MappedBy
	private int idActualite;
	@MappedBy
	private String typeActuailite;
	@MappedBy
	private User user;
	@MappedBy
	private int who;
	@MappedBy
	private Date date;
	@MappedBy
	private WhoDto whoDto;
	public int getIdActualite() {
		return idActualite;
	}
	public void setIdActualite(int idActualite) {
		this.idActualite = idActualite;
	}
	public String getTypeActuailite() {
		return typeActuailite;
	}
	public void setTypeActuailite(String typeActuailite) {
		this.typeActuailite = typeActuailite;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getWho() {
		return who;
	}
	public void setWho(int who) {
		this.who = who;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public WhoDto getWhoDto() {
		return whoDto;
	}
	public void setWhoDto(WhoDto whoDto) {
		this.whoDto = whoDto;
	}
	public ActualiteDto(int idActualite, String typeActuailite, User user, int who, Date date, WhoDto whoDto) {
		super();
		this.idActualite = idActualite;
		this.typeActuailite = typeActuailite;
		this.user = user;
		this.who = who;
		this.date = date;
		this.whoDto = whoDto;
	}
	public ActualiteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
