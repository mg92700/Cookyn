package com.general.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jtransfo.DomainClass;
import org.jtransfo.MappedBy;
import org.jtransfo.NotMapped;

import lombok.Data;

@Data
@DomainClass("com.general.model.User")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDto {
	@MappedBy
	private Integer idUser;
	@MappedBy
	private String nomUser;
	@MappedBy
	private String prenomUser;
	@MappedBy
	private String mailUser;
	@MappedBy
	private String passwordUser;
	@MappedBy
	private String usernameUser;
	@MappedBy
	private String villeUser;
	@NotMapped
	private String newPassword;
	@NotMapped
	private int nbRecetteCreate;
	@NotMapped
	private int nbRecetteFav;
	@NotMapped
	private int nbFollower;
	@NotMapped 
	private int nbFollowing;
	@NotMapped 
	private String Errortxt;
	
	public String getErrortxt() {
		return Errortxt;
	}
	public void setErrortxt(String errortxt) {
		Errortxt = errortxt;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public String getMailUser() {
		return mailUser;
	}
	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	public String getUsernameUser() {
		return usernameUser;
	}
	public void setUsernameUser(String usernameUser) {
		this.usernameUser = usernameUser;
	}
	public String getVilleUser() {
		return villeUser;
	}
	public void setVilleUser(String villeUser) {
		this.villeUser = villeUser;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public int getNbRecetteCreate() {
		return nbRecetteCreate;
	}
	public void setNbRecetteCreate(int nbRecetteCreate) {
		this.nbRecetteCreate = nbRecetteCreate;
	}
	public int getNbRecetteFav() {
		return nbRecetteFav;
	}
	public void setNbRecetteFav(int nbRecetteFav) {
		this.nbRecetteFav = nbRecetteFav;
	}
	public int getNbFollower() {
		return nbFollower;
	}
	public void setNbFollower(int nbFollower) {
		this.nbFollower = nbFollower;
	}
	public int getNbFollowing() {
		return nbFollowing;
	}
	public void setNbFollowing(int nbFollowing) {
		this.nbFollowing = nbFollowing;
	}
	public UserDto(Integer idUser, String nomUser, String prenomUser, String mailUser, String passwordUser,
			String usernameUser, String villeUser, String newPassword, int nbRecetteCreate, int nbRecetteFav,
			int nbFollower, int nbFollowing,String Errortxt) {
		super();
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.mailUser = mailUser;
		this.passwordUser = passwordUser;
		this.usernameUser = usernameUser;
		this.villeUser = villeUser;
		this.newPassword = newPassword;
		this.nbRecetteCreate = nbRecetteCreate;
		this.nbRecetteFav = nbRecetteFav;
		this.nbFollower = nbFollower;
		this.nbFollowing = nbFollowing;
		this.Errortxt=Errortxt;
	}
	public UserDto() {
		super();
	}
}
