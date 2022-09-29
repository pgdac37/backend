package com.pizza.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "toppings")
public class Toppings {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int toppingId;
	private String toppingName;
	private double price;
	
	
}