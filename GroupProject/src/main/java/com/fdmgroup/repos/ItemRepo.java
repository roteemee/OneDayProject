package com.fdmgroup.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.classes.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
