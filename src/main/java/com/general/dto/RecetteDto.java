package com.general.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jtransfo.DomainClass;
import org.jtransfo.MappedBy;
import org.jtransfo.NotMapped;

import com.general.model.Etape;
import com.general.model.Recette;
import com.general.model.RecetteIngredient;

import lombok.Data;

@Data
@DomainClass("com.general.model.Recette")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RecetteDto {
	@MappedBy
	private Recette recette;
	@MappedBy
	private List<RecetteIngredient> ingredients;
	@MappedBy
	private List<Etape> etapes;
	
	@NotMapped 
	private String imageRecette;
	

	public RecetteDto(Recette recette, List<RecetteIngredient> ingredients, List<Etape> etapes,String imageRecette) {
		super();
		this.recette = recette;
		this.ingredients = ingredients;
		this.etapes = etapes;
		this.imageRecette=imageRecette;
	}


	public RecetteDto() {
		super();
	}
	

	public String getImageRecette() {
		return imageRecette;
	}


	public void setImageRecette(String imageRecette) {
		this.imageRecette = imageRecette;
	}


	public Recette getRecette() {
		return recette;
	}


	public void setRecette(Recette recette) {
		this.recette = recette;
	}


	public List<RecetteIngredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(List<RecetteIngredient> ingredients) {
		this.ingredients = ingredients;
	}


	public List<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<Etape> etapes) {
		this.etapes = etapes;
	}

}
