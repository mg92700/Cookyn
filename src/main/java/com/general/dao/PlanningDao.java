package com.general.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.model.Planning;
import com.general.model.User;

public interface PlanningDao extends JpaRepository<Planning, Long> {
	
	List<Planning> findAllByuser(User user);
	
	Planning findByidPlanning(int idplanning);
	
	/*
	
	@Query("SELECT p FROM Planningp WHERE p.libelleRecette = :user and p.datePlanning BETWEEN :dateDebut and :dateFin")
	List<Planning> findPlanningByUserAndDate(@Param(value = "user") User user, @Param(value = "dateDebut") Date dateDebut,
			@Param(value = "dateFin") Date dateFin);
	
	
	**/
	
}
