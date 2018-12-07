package com.general.dto;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jtransfo.DomainClass;
import org.jtransfo.MappedBy;
import org.jtransfo.NotMapped;

import java.util.Date;

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
	@MappedBy
	private String role;
	@NotMapped
	private String newPassword;
	@NotMapped
	private int nbRecetteCreate;
	@NotMapped
	private int nbRecetteFav;
	@NotMapped
	private int nbAbonnement;
	@NotMapped 
	private int nbAbonnee;
	@NotMapped 
	private String Errortxt;
	@NotMapped 
	private String Token;
	
	@MappedBy
	private Date dateCreation;
	@MappedBy
	private Date dateModification;
	
	public UserDto(Integer idUser, String nomUser, String prenomUser, String mailUser, String passwordUser,
			String usernameUser, String villeUser, String role, String newPassword, int nbRecetteCreate,
			int nbRecetteFav, int nbAbonnement, int nbAbonnee, String errortxt, String token, Date dateCreation,
			Date dateModification) {
		super();
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.mailUser = mailUser;
		this.passwordUser = passwordUser;
		this.usernameUser = usernameUser;
		this.villeUser = villeUser;
		this.role = role;
		this.newPassword = newPassword;
		this.nbRecetteCreate = nbRecetteCreate;
		this.nbRecetteFav = nbRecetteFav;
		this.nbAbonnement = nbAbonnement;
		this.nbAbonnee = nbAbonnee;
		Errortxt = errortxt;
		Token = token;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateModification() {
		return dateModification;
	}
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public int getNbAbonnement() {
		return nbAbonnement;
	}
	public void setNbAbonnement(int nbAbonnement) {
		this.nbAbonnement = nbAbonnement;
	}
	public int getNbAbonnee() {
		return nbAbonnee;
	}
	public void setNbAbonnee(int nbAbonnee) {
		this.nbAbonnee = nbAbonnee;
	}
	public String getErrortxt() {
		return Errortxt;
	}
	public void setErrortxt(String errortxt) {
		Errortxt = errortxt;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(Integer idUser, String nomUser, String prenomUser, String mailUser, String passwordUser,
			String usernameUser, String villeUser, String role, String newPassword, int nbRecetteCreate,
			int nbRecetteFav, int nbAbonnement, int nbAbonnee, String errortxt, String token) {
		super();
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.mailUser = mailUser;
		this.passwordUser = passwordUser;
		this.usernameUser = usernameUser;
		this.villeUser = villeUser;
		this.role = role;
		this.newPassword = newPassword;
		this.nbRecetteCreate = nbRecetteCreate;
		this.nbRecetteFav = nbRecetteFav;
		this.nbAbonnement = nbAbonnement;
		this.nbAbonnee = nbAbonnee;
		Errortxt = errortxt;
		Token = token;
	}
	
	

	
	
}
