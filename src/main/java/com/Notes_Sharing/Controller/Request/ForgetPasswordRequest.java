package com.Notes_Sharing.Controller.Request;

import lombok.Data;

@ Data
public class ForgetPasswordRequest {
	private String email;
	private long mobileNo;
	private String newPassword;
	private String confirmPassword;

}
