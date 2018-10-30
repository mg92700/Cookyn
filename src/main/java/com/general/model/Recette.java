package com.general.model;

import java.sql.Blob;

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
public class Recette{
	
	@Id
	@GeneratedValue
	private Integer idRecette;
	@Column
	private String catRecette;
	@Column
	private String libelleRecette;
	@Column
	private int tempPrepaRecette;
	@Column
	private String diffRecette;
	@ManyToOne
	@JoinColumn(name="creeparUser")
	private User user;
	@Column
	private Blob photoRecette;
	
	public Recette(Integer idRecette, String catRecette, String libelleRecette, int tempPrepaRecette, String diffRecette,
			User user, Blob photoRecette) {
		super();
		this.idRecette = idRecette;
		this.catRecette = catRecette;
		this.libelleRecette = libelleRecette;
		this.tempPrepaRecette = tempPrepaRecette;
		this.diffRecette = diffRecette;
		this.user = user;
		this.photoRecette = photoRecette;
	}

	public Recette() {
		super();
	}
	
	public Integer getIdRecette() {
		return idRecette;
	}
	public void setIdRecette(Integer idRecette) {
		this.idRecette = idRecette;
	}
	public String getCatRecette() {
		return catRecette;
	}
	public void setCatRecette(String catRecette) {
		this.catRecette = catRecette;
	}
	public String getLibelleRecette() {
		return libelleRecette;
	}
	public void setLibelleRecette(String libelleRecette) {
		this.libelleRecette = libelleRecette;
	}
	public int getTempPrepaRecette() {
		return tempPrepaRecette;
	}
	public void setTempPrepaRecette(int tempPrepaRecette) {
		this.tempPrepaRecette = tempPrepaRecette;
	}
	public String getDiffRecette() {
		return diffRecette;
	}
	public void setDiffRecette(String diffRecette) {
		this.diffRecette = diffRecette;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Blob getPhotoRecette() {
		return photoRecette;
	}
	public void setPhotoRecette(Blob photoRecette) {
		this.photoRecette = photoRecette;
	}		
	
}


/*

	idRecette int primary key auto_increment,
    catRecette varchar(50),
    libelleRecette varchar(50),
    tempPrepaRecette int,
    diffRecette varchar(10) check (diffRecette in ('Facile','Moyen','Difficle')),
    creeparUser int,
	datecreationidUser date,
    photoRecette blob,
    FOREIGN KEY (creeparUser) REFERENCES User(idUser)

*/