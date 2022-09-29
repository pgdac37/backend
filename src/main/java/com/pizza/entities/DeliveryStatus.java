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
@Table(name="deliverystatus")
public class DeliveryStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deliveryId;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="payId")
	private Payments payments;
	@ManyToOne
	@JoinColumn(name="userId")
	private Users users;
	@ManyToOne
	@JoinColumn(name="AddressId")
	private Address address;
	private String deliveryStatus;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false)
	private Date deliveryTime;
	
	
}
