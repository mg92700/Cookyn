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
@IdClass(Note.class)
public class Note implements Serializable {
	
	@Id
	@ManyToOne	
	@JoinColumn(name="idUser")
	private User user;
	@Id
	@ManyToOne
	@JoinColumn(name="idRecette")
	private Recette recette;
	@Column
	private int note;
		
	public Note(User user, Recette recette, int note) {
		super();
		this.user = user;
		this.recette = recette;
		this.note = note;
	}
	
	
	public Note() {
		super();
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Recette getRecette() {
		return recette;
	}
	public void setRecette(Recette recette) {
		this.recette = recette;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	
	
	
}



/*


idUser int,
    idRecette int,
    note int check (note between 1 and 5),
    FOREIGN KEY (idUser) REFERENCES User(idUser),
    FOREIGN KEY (idRecette) REFERENCES Recette(idRecet


*/