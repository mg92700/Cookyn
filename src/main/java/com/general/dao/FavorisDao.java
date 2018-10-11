package com.general.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.model.Etape;
import com.general.model.Favoris;
import com.general.model.Recette;
import com.general.model.User;


public interface FavorisDao extends JpaRepository<Favoris, Long> {
	
	List<Favoris> findAllByuser(User user);	
}