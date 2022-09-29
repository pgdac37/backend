package com.pizza.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pizza.entities.Users;
import com.pizza.repo.UserRepository;

@Service
@Transactional
public class PizzaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userRepository.getUserByUsername(username);

		if (user == null)
			throw new UsernameNotFoundException("Could not find user");

		return new PizzaUserDetails(user);
	}

}
