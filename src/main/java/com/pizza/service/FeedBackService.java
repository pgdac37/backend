package com.pizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizza.repo.FeedBackDao;
import com.pizza.entities.Feedback;

@Service
@Transactional
public class FeedBackService {

	@Autowired
	private FeedBackDao feedbackDao;

	//add new feedback to list
	public Feedback addNewFeedback(Feedback feedback) {
		feedbackDao.save(feedback);
		return feedback;	
	}

	public List<Feedback> getAll() {
		// TODO Auto-generated method stub
		return feedbackDao.findAll();
	}

}
