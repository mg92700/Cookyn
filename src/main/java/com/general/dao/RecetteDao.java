package com.general.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.general.model.Recette;

public interface RecetteDao extends JpaRepository<Recette, Long> {

	@Query("SELECT r FROM Recette r WHERE r.libelleRecette = ?1")
	List<Recette> findAllWhereNom(String name);
	
	Recette findByidRecette(int idRecette);
	
	List<Recette> findBylibelleRecette(String libelleRecette);
	
	
}