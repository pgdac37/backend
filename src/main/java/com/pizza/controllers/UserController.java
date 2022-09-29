package com.pizza.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.dto.CredentialsDto;
import com.pizza.dto.NewPasswordDTO;
import com.pizza.dto.UserDto;
import com.pizza.dto.UserSignUpDTO;
import com.pizza.entities.PasswordResetToken;
import com.pizza.entities.Users;
import com.pizza.security.EmailSender;
import com.pizza.security.JwtUtils;
import com.pizza.service.UserService;
//http://localhost:3000
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"},allowedHeaders = "*",allowCredentials ="true")
@RequestMapping("/user")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils utils;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private EmailSender emailService;
	@Autowired
    private ModelMapper modelMapper;
	//dep : user service for handling users
//	@Autowired
//	private UserService PizzaUserService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid CredentialsDto request) {
		// store incoming user details(not yet validated) into Authentication object
		// Authentication i/f ---> implemented by UserNamePasswordAuthToken
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
//		log.info("auth token " + authToken);
		System.out.println("auth token " + authToken);
		try {
			// authenticate the credentials
			Authentication authenticatedDetails = manager.authenticate(authToken);
//			log.info("auth token again " + authenticatedDetails);
			System.out.println("auth token again " + authenticatedDetails);
			// => auth succcess
			
			String jwt = utils.generateJwtToken(authenticatedDetails);
			
			
			String rc = ResponseCookie.from("token",  jwt)
					.maxAge(1000)
					.httpOnly(true)
					.path("/")
					.build()  // create http cookie
					.toString();
			
			HttpHeaders responseHeader = new HttpHeaders();
			responseHeader.add(HttpHeaders.SET_COOKIE, rc);
			//return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails)));
			Users userSignIn = userService.getUser(request);
			if(userSignIn.getRole().equals("User"))
				return ResponseEntity.ok().headers(responseHeader).body(new AuthResp(userSignIn, jwt,"User Auth Successful!"));
			else if(userSignIn.getRole().equals("Admin"))
				return ResponseEntity.ok().headers(responseHeader).body(new AuthResp(userSignIn, jwt, "Admin Auth successful!"));
			else
				throw new BadCredentialsException("Not user or Admin");
		} catch (BadCredentialsException e) { // lab work : replace this by a method in global exc handler
			// send back err resp code
			System.out.println("err "+e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}

	}

	@GetMapping("/{email}")
	public ResponseEntity<?> findById(@PathVariable("email") String email) {
		try {
			Users result = userService.searchByEmail(email);
			if (result == null)
				return Response.error("Not found");
			return Response.success(result);
		} catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}

	// API for sign up of User/Admin
//	@PostMapping("/signup")
//	public ResponseEntity<?> SignUpUser(@Valid @RequestBody Users user){
//		try {
//			if(user.getEmail() == null) throw new Exception("blank");
//			Users newUserEmailVerify=userService.searchByEmail(user.getEmail());
//			Users newUserPhVerify=userService.searchByPhone(user.getPhoneNo());
//			System.out.println(newUserEmailVerify);
//			System.out.println(newUserPhVerify);
//			if(newUserEmailVerify==null && newUserPhVerify==null) {
//				Users add=userService.addUser(user);
//				return Response.success(add);
//			}else if(newUserEmailVerify !=null) {
//				return Response.error("Email already exist");
//			}else if(newUserPhVerify != null) {
//				return Response.error("Phone Number already exists");
//			}else {
//				return Response.error("Something went wrong");
//			}
//			
//		} catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
//	}

	// API for sign up of User/Admin
	@PostMapping("/signup")
	public ResponseEntity<?> SignUpUser(@Valid @RequestBody UserSignUpDTO user) {
		System.out.println(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(user));
	}
	

	// API for sign in of User/Admin

	@PostMapping("/mee")
	public ResponseEntity<?> signInUser(@RequestBody CredentialsDto cred) {
		
		return ResponseEntity.ok().body(userService.getUser(cred));

//		
//		System.out.println(cred);
//		Users userSignIn = userService.getUser(cred);
//		if (userSignIn != null) {
//			return Response.success(userSignIn);
//		} else {
//			return Response.error("Invalid Credentials");
//		}
	}
	
	
	@PostMapping("/logoutpost")
	@PreAuthorize("hasRole('User')")  
//	@RolesAllowed("dfsfsfsf")
	
	public ResponseEntity<?> logout(@CookieValue(name = "token") String token){
		
		utils.destroyJWT(token);
		
		
		return Response.success("OK");
		
	}
	 
	
	//to update user
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> editUsers(@PathVariable("userId") int  userId,
			@RequestBody UserDto dto)
	{
		
		return ResponseEntity.ok().body(userService.editUsers(userId, dto));

//		try {
//			Users editUsers=userService.editUsers(userId, dto);
//			System.out.println("THIS IS=>>>"+editUsers);
//			if(editUsers!=null) {
//				return Response.success(editUsers);
//			}else {
//				return Response.error("Something went wrong");
//			}
//		} catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") int  userId)
	{
		
		return ResponseEntity.ok().body(userService.deleteUser(userId));
	
	}
	
	@GetMapping("/profile")
	public ResponseEntity<?> getAllUsers(){
		
		return ResponseEntity.ok().body(userService.getAllUsers());
	
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody CredentialsDto cred) {
	    Users user = userService.searchByEmail(cred.getEmail());
	    
	    String token = UUID.randomUUID().toString();
	    userService.createPasswordResetTokenForUser(user, token);
	    
	    System.out.println(token + " -- " + user.getEmail());
	    emailService.sendEmail(user.getEmail(), "password reset", token);
	    return  ResponseEntity.ok().body("Email Sent");
	}
	
	@PostMapping("/newPassword")
	public ResponseEntity<?> newPassword(@RequestBody NewPasswordDTO newPassword){
	    
		PasswordResetToken prt = userService.validatePasswordResetToken(newPassword.getToken()); 
		System.out.println(" token valid: " + prt);
		userService.resetPassword(prt, newPassword);
		
		return  ResponseEntity.ok().body("Password Reset");
	}

}
