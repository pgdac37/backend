package com.pizza.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils utils;
	@Autowired
	private PizzaUserDetailsService pizzaUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("in once per request filter");
		System.out.println(request.getCookies());
		// get authorization header n check if not null n starting with Bearer
		String header = request.getHeader("Authorization");
		System.out.println(header);
		if (header != null /*&& header.startsWith(""))*/ || request.getCookies() != null){
			// Bearer token present --> extract n validate it
			String token = "a";
			if(header !=null)  token = header.substring(7);
			else {
				for(Cookie c: request.getCookies())
					if(c.getName().equals("token")) 
						token = c.getValue();
			}
			
			
			if (utils.validateJwtToken(token)) {
				// valid token --> extract user name from the token
				String userName = utils.getUserNameFromJwtToken(token);
				
				if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					// load user details from UserDetailsService
					System.out.println("security context is null");
					UserDetails userDetails = pizzaUserDetailsService.loadUserByUsername(userName);
					// create Authentication object , wrapping user details lifted from DB
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
					//set details in auth object
		//			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
       //			Save this authentication token in the sec ctx.
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
				else
					log.info("user name null or authentication already set , username {}",userName);

			}
		} else
			log.error("Request header DOES NOT contain a Bearer Token");
		if(request.getCookies() != null) {
			Cookie cookies[] = request.getCookies();
			for(Cookie c : cookies) {
				System.out.println(c.getName());
				System.out.println(c.getValue());
				
			}
				
		}
		
		//pass the request to the next filter in the chain
		filterChain.doFilter(request, response);

	}

}
