package com.general.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Favoris implements Serializable {
	
	@Id
	@GeneratedValue
	private int idFavoris;
	
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	@ManyToOne
	@JoinColumn(name="idRecette")
	private Recette recette;
	
	
	public Favoris() {
		super();
	}
	public Favoris(int idFavoris,User user, Recette recette) {
		super();
		this.idFavoris=idFavoris;
		this.user = user;
		this.recette = recette;
	}
	
	
	public int getIdFavoris() {
		return idFavoris;
	}
	public void setIdFavoris(int idFavoris) {
		this.idFavoris = idFavoris;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Recette getRecette() {
		return recette;
	}
	public void setRecette(Recette recette) {
		this.recette = recette;
	}	
	
	
}