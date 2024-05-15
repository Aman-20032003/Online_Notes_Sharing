package com.Notes_Sharing.Controller.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data

@AllArgsConstructor
public class RegistrationRequest {
	 private String name ;
	 private long mobileNo;
	 private String email;
	 private String password;
	 
	 

}