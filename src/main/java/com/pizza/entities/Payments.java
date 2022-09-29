package com.pizza.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="payments")
public class Payments {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int payId;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="userId")
	private Users users ;
	private double totalAmount;
	private String payStatus;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false)
	private Date payTimeStamp;
	private String mode;
	
	
}
