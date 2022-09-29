package com.pizza.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="feedback")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int feedbackId;
	@Column(name="FirstName")
	private String firstName;	
	@Column(name="LastName")
	private String lastName;
	private String email;
	private String phoneNo;
	private String feedback;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false)
	private Date feedBackTime;
}
