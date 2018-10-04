package com.general.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jtransfo.DomainClass;
import org.jtransfo.MappedBy;

import lombok.Data;

@Data
@DomainClass("com.general.model.User")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDto {
	@MappedBy
	private Long id;
	@MappedBy
	private String pseudo;
	@MappedBy
	private String urlImage;
	@MappedBy
	private String password;
	@MappedBy
	private String email;
	@MappedBy
	private int nbFollow;
	@MappedBy
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
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", pseudo=" + pseudo + ", urlImage=" + urlImage + ", username=" + password
				+ ", email=" + email + ", nbFollow=" + nbFollow + ", nbFollower=" + nbFollower + "]";
	}
	public UserDto(Long id, String pseudo, String urlImage, String password, String email, int nbFollow,
			int nbFollower) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.urlImage = urlImage;
		this.password = password;
		this.email = email;
		this.nbFollow = nbFollow;
		this.nbFollower = nbFollower;
	}
}
