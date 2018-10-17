package com.general.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.general.model.Relation;
import com.general.model.User;

public interface RelationDao extends JpaRepository<Relation, Long> {

	List<Relation> findAllByFriend(User u);

	List<Relation> findAllByUser(User u);
		
}
