package com.general.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.general.model.Planning;
import com.general.model.User;

public interface PlanningDao extends JpaRepository<Planning, Long> {
	
	List<Planning> findAllByuser(User user);
	
	Planning findByidPlanning(int idplanning);
	
}
