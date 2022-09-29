package com.pizza.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
@Table(name = "toppingImage")
public class ToppingImages {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int toppingImgId;
	@OneToOne
	@JoinColumn(name = "toppingId")
	private Toppings toppings;
	@Lob
	private byte[] Data;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false)
	private Date toppingImgTime;
	

}
