package com.general.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.general.model.Ingredient;
import com.general.model.Recette;
import com.general.model.Unite;

public interface UniteDao extends JpaRepository<Unite, Long> {

	@Query("SELECT u FROM Unite u WHERE u.libelleUnite = ?1")
	List<Unite> findAllWhereNom(String name);
	
	Unite findBylibelleUnite(String libelleUnite);
	
	Unite findByidUnite(int idUnite);
	
}
