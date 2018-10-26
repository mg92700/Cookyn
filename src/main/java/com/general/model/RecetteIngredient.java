package com.general.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@IdClass(RecetteIngredient.class)
public class RecetteIngredient implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name="idRecette")
	private Recette recette;
	@Id
	@ManyToOne
	@JoinColumn(name="idIngredient")
	private Ingredient ingredient;
	@ManyToOne
	@JoinColumn(name="idUnite")
	private Unite unite;
	@Column
	private float quantite;
	
	
	
	public RecetteIngredient(Recette recette, Ingredient ingredient, Unite unite, float quantite) {
		super();
		this.recette = recette;
		this.ingredient = ingredient;
		this.unite = unite;
		this.quantite = quantite;
	}


	public RecetteIngredient() {
		super();
	}
	
	
	public Recette getRecette() {
		return recette;
	}
	public void setRecette(Recette recette) {
		this.recette = recette;
	}
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	public Unite getUnite() {
		return unite;
	}
	public void setUnite(Unite unite) {
		this.unite = unite;
	}
	public float getQuantite() {
		return quantite;
	}
	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}
	
	
}


/*


	idRecette int,
    idIngredient int,
    idUnite int,
	quantite float,
    FOREIGN KEY (idRecette) REFERENCES Recette(idRecette),
    FOREIGN KEY (idIngredient) REFERENCES Ingredient(idIngredient),
    FOREIGN KEY (idUnite) REFERENCES Unite(idUnite),
    primary key (idRecette,idIngredient)



*/