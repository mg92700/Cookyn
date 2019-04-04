    
package com.general.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.general.model.Actualite;
import com.general.model.Etape;
import com.general.model.Favoris;
import com.general.model.Ingredient;
import com.general.model.User;


public interface ActualiteDao extends JpaRepository<Actualite, Integer> {

	List<Actualite> findAllByuser(User user);
	
    @Query("SELECT a FROM Actualite a WHERE a.user=?1 and a.date >= ?2 and a.date <= ?3")
    List<Actualite> findAllByuserAndPeriod(User user,Date date1,Date date2);
}