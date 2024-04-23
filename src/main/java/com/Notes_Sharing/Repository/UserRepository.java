package com.Notes_Sharing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Notes_Sharing.Repository.Entity.User;

public interface  UserRepository extends JpaRepository<User, Integer >{
	public User findUserByEmail(String email);
	public User findUserByEmailAndPassword(String email,String password);
	
}
