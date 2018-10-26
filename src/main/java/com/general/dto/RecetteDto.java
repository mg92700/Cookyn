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

import com.general.model.Etape;
import com.general.model.Ingredient;
import com.general.model.Recette;
import com.general.model.User;

import lombok.Data;

@Data
@DomainClass("com.general.model.Recette")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RecetteDto {
	@MappedBy
	private Recette recette;
	@MappedBy
	private List<Ingredient> ingredients;
	@MappedBy
	private List<Etape> etapes;
	


	public RecetteDto(Recette recette, List<Ingredient> ingredients, List<Etape> etapes) {
		super();
		this.recette = recette;
		this.ingredients = ingredients;
		this.etapes = etapes;
	}


	public RecetteDto() {
		super();
	}
	

	public Recette getRecette() {
		return recette;
	}


	public void setRecette(Recette recette) {
		this.recette = recette;
	}


	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<Etape> etapes) {
		this.etapes = etapes;
	}

}
