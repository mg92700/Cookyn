package com.general.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User{
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String pseudo;
	@Column(nullable = false)
	private String urlImage;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private int nbFollow;
	@Column(nullable = false)
	private int nbFollower;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNbFollow() {
		return nbFollow;
	}
	public void setNbFollow(int nbFollow) {
		this.nbFollow = nbFollow;
	}
	public int getNbFollower() {
		return nbFollower;
	}
	public void setNbFollower(int nbFollower) {
		this.nbFollower = nbFollower;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String pseudo, String urlImage, String password, String email, int nbFollow, int nbFollower) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.urlImage = urlImage;
		this.password = password;
		this.email = email;
		this.nbFollow = nbFollow;
		this.nbFollower = nbFollower;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pseudo=" + pseudo + ", urlImage=" + urlImage + ", password=" + password
				+ ", email=" + email + ", nbFollow=" + nbFollow + ", nbFollower=" + nbFollower + "]";
	}
}
