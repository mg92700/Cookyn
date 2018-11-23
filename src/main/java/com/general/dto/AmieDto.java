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
public class AmieDto {
	
	@MappedBy
	private Integer idUser;
	@MappedBy
	private String nomUser;
	@MappedBy
	private String prenomUser;
	@MappedBy
	private String userNameUser;
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
	public String getUserNameUser() {
		return userNameUser;
	}
	public void setUserNameUser(String userNameUser) {
		this.userNameUser = userNameUser;
	}
	public AmieDto(Integer idUser, String nomUser, String prenomUser, String userNameUser) {
		super();
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.userNameUser = userNameUser;
	}
	@Override
	public String toString() {
		return "AmieDto [idUser=" + idUser + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", userNameUser="
				+ userNameUser + "]";
	}
	public AmieDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	
	
	

}
