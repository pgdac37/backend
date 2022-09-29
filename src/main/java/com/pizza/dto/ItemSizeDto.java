package com.pizza.dto;

import javax.validation.constraints.NotBlank;

import com.pizza.entities.Item;

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
public class ItemSizeDto {
	@NotBlank(message ="Item details not found")	
	private int itemId;
	private String size;
	private double price;

}
