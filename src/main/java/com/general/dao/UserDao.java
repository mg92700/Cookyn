package com.general.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.general.model.User;


public interface UserDao extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.usernameUser = ?1")
	List<User> findAllWhereNom(String name);
	
	User findByusernameUser(String usernameUser);
	
	
}