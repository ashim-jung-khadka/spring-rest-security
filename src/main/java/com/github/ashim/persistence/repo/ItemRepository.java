package com.github.ashim.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.ashim.persistence.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	public Item findById(Integer id);

}