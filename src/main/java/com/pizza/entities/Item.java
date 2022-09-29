package com.pizza.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
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
@Table(name = "item")
public class Item  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemid;
	private String type;
	private String itemName;
	private String description;
	
	public Item(int itemid) {
		this.itemid = itemid;
	}
	
}
