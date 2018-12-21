package com.general.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jtransfo.NotMapped;

import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseListDto {
	
	@NotMapped 
	String libelleIngredient;
	@NotMapped 
	float quantite;
	
	@NotMapped 
	int idIngredient;

	@NotMapped 
	String unite;
	
	
	@NotMapped 
	String categorie;
	

	public String getLibelleIngredient() {
		return libelleIngredient;
	}

	public void setLibelleIngredient(String libelleIngredient) {
		this.libelleIngredient = libelleIngredient;
	}

	public float getQuantite() {
		return quantite;
	}

	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}

	public int getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(int idRecette) {
		this.idIngredient = idRecette;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public CourseListDto(String libelleIngredient, float quantite, int idIngredient, String unite, String categorie) {
		super();
		this.libelleIngredient = libelleIngredient;
		this.quantite = quantite;
		this.idIngredient = idIngredient;
		this.unite = unite;
		this.categorie = categorie;
	}

	public CourseListDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	

}
