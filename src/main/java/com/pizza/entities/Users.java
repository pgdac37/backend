package com.pizza.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class Users {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="user_id")
	private int userId;
	@Column(name="first_name",length = 30,nullable = false)
	private String firstName;
	@Column(name="last_name",length = 30,nullable = false)
	private String lastName;
	@Column(length = 30,nullable = false,unique = true)
	private String email;
	@Column(name="phone_no",length = 10,nullable = false,unique=true)
	private String phoneNo;
	@Column(nullable = false)
	private String password;
	@Column(length = 30,nullable = false)
	private String role;
}
