package com.pizza.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "itemImage")
public class ItemImage{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemImgId;
	@OneToOne
	@JoinColumn(name = "itemId")
	private Item item;
	@Lob
	private byte [] data;
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false)
	private LocalDate itemImgTime;
	
	
}
