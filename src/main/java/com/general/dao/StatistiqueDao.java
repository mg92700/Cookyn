package com.general.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.general.model.Recette;
import com.general.model.Statistique;

public interface StatistiqueDao  extends JpaRepository<Statistique, Long>  {

	
	@Query("SELECT r FROM Statistique r WHERE r.libelleStatistique = ?1")
	Statistique findByLibelle(String name);
}
