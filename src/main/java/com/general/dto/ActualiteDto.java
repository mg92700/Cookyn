package com.general.dto;

import java.util.Date;

import org.jtransfo.MappedBy;

import com.general.model.User;

public class ActualiteDto {
	
	@MappedBy
	private int idActualite;
	@MappedBy
	private String typeActualite;
	@MappedBy
	private User user;
	@MappedBy
	private int idWho;
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
	public String getTypeActualite() {
		return typeActualite;
	}
	public void setTypeActualite(String typeActualite) {
		this.typeActualite = typeActualite;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getIdWho() {
		return idWho;
	}
	public void setIdWho(int idWho) {
		this.idWho = idWho;
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
	public ActualiteDto(int idActualite, String typeActualite, User user, int idWho, Date date, WhoDto whoDto) {
		super();
		this.idActualite = idActualite;
		this.typeActualite = typeActualite;
		this.user = user;
		this.idWho = idWho;
		this.date = date;
		this.whoDto = whoDto;
	}
	public ActualiteDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
