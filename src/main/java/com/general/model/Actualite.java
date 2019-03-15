package com.general.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Actualite {

	
	
	@Id
	@GeneratedValue
	private int idActualite;
	@Column
	private String descriptionEtape;
	@Column
	private int indexEtape;
	@ManyToOne
	@JoinColumn(name="idRecette")
	private Recette recette;
}
