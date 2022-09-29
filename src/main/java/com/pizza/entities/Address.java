package com.pizza.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

@Entity
@Table(name="address")
public class Address {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int AddressId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private Users user;
	private String plotNo;
	private String streetName;
	private String city;
	private String district;
	private String soverignState;
	private String pincode;
	
}