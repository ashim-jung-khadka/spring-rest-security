package com.github.ashim.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.ashim.persistence.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	public User findById(Integer id);

	public User findByUserName(String userName);

	@Query("Select u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) "
			+ "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
	public List<User> search(@Param("searchTerm") String searchTerm);

}
