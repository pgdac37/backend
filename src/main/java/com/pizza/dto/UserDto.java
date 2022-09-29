package com.pizza.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class UserDto {
	
	private int userId;
	private String firstName;
	
	private String lastName;
	@Email
	@NotBlank
	private String email;
	@Email
	@NotBlank
	private String password;
	
	private String role;
	
	private String phoneNo;

	
}
