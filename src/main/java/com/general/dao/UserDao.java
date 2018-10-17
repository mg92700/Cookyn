package com.general.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.general.model.User;


public interface UserDao extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.usernameUser = ?1")
	List<User> findAllWhereNom(String name);
	
	@Query("SELECT u FROM User u WHERE u.mailUser = ?1")
	List<User> findAllWhereMail(String mail);
	
	User findByUsernameUser(String usernameUser);
	
	User findByMailUser(String mailUser);
	
	User findUserByIdUser(int idUser);
	
}