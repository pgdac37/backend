package com.pizza.dto;

import java.util.Date;

import com.pizza.entities.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewPasswordDTO {
	
	private String token;
	
	private String password;

}
 
