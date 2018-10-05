package com.general.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Favoris implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	@Id
	@ManyToOne
	@JoinColumn(name="idRecette")
	private Recette recette;	
}