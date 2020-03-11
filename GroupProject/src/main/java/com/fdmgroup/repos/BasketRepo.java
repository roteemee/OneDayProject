package com.fdmgroup.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.classes.Basket;

public interface BasketRepo extends JpaRepository <Basket, Integer> {

}
