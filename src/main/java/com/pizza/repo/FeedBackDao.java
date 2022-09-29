package com.pizza.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.entities.Feedback;

public interface FeedBackDao extends JpaRepository<Feedback, Integer>{

   
}