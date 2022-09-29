package com.pizza.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="itemsize")
public class ItemSize {
	public ItemSize(Item item2, String size2, double price2) {
		this.item = item2;
		this.size = size2;
		this.price = price2;
	}
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int sizeId;
	@ManyToOne(cascade =CascadeType.MERGE)
	@JoinColumn(name="itemId")
	private Item item;
	private String size;
	private double price;
	
}
