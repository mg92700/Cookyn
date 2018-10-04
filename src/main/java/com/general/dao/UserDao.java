package com.general.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.general.model.User;


public interface UserDao extends JpaRepository<User, Long> {

	User findByPseudo(String pseudo);
	User findByEmail(String email);

}