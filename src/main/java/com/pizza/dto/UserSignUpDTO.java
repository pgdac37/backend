package com.pizza.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

public class UserSignUpDTO {
	private int userId;
	@NotBlank(message = "first name is required!!")
	private String firstName;
	@NotBlank(message = "last name is required!!")
	private String lastName;
	@Email
	@NotBlank(message = "email is required!!")
	private String email;
	@Size(min = 5, max = 10)
	@NotBlank(message = "password is required!!")
	private String password;
	@Size(min = 10, max = 10)
	@NotBlank(message = "Phone number is required!!")
	private String phoneNo;
}
