package com.practice.springApp;

import lombok.Data;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Relation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="RelationId")
	private Long relationId;
	@Column(name="ParticipantId")
	private Long participantId;
	@Column(name="First")
	private String first;
	@Column(name="Middle")
	private String middle;
	@Column(name="Last")
	private String last;
	@Column(name="DOB")
	private Date dob;
	@Column(name="SSN")
	private String ssn;
	@Column(name="SSNKEY")
	private String ssnKey;
	@Column(name="Phone")
	private String phone;
	@Column(name="AddressId")
	private Long addressId;
	@Column(name="RelationshipType")
	private String relationshipType;

}
