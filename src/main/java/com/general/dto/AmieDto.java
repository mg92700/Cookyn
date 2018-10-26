package com.general.dto;


import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jtransfo.DomainClass;
import org.jtransfo.MappedBy;
import org.jtransfo.NotMapped;

import com.general.model.Etape;
import com.general.model.Ingredient;
import com.general.model.Recette;
import com.general.model.User;


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
	public AmieDto(Integer idUser, String nomUser, String prenomUser) {
		super();
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
	}
	public AmieDto() {
		super();
	}
	
	
	
	
	
	
	
	

}
