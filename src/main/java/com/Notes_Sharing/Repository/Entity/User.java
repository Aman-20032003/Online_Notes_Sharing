package com.Notes_Sharing.Repository.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "Name Cannot Be Null")
	private String name;
	private long mobileNo;

	@NotEmpty(message = "Email Cannot Be Null")
	private String email;

	@NotEmpty(message = "Password Cannot Be Null")
	private String password;
	
	@ManyToOne
private	Subject subject;
	
	
}
