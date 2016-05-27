package com.github.ashim.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.ashim.persistence.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findById(Integer id);

	public User findByUsername(String username);

}
