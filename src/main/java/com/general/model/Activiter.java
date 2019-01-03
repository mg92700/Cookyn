package com.general.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Activiter {
	
	
	@Id
	@GeneratedValue
	private int idActiviter;
	@Column
	private String typeActiviter;
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	@Column
	private int idWhat;//1create add , 2 follow , 3 unfollow, 4 favoris
	@Column
	private Date date;
	
	public int getIdActiviter() {
		return idActiviter;
	}
	public void setIdActiviter(int idActiviter) {
		this.idActiviter = idActiviter;
	}
	public String getTypeActiviter() {
		return typeActiviter;
	}
	public void setTypeActiviter(String typeActiviter) {
		this.typeActiviter = typeActiviter;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getIdWhat() {
		return idWhat;
	}
	public void setIdWhat(int idWhat) {
		this.idWhat = idWhat;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Activiter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Activiter(int idActiviter, String typeActiviter, User user, int idWhat, Date date) {
		super();
		this.idActiviter = idActiviter;
		this.typeActiviter = typeActiviter;
		this.user = user;
		this.idWhat = idWhat;
		this.date = date;
	}
	
	
	

}
