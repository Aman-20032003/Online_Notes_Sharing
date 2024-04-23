package com.Notes_Sharing.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Notes_Sharing.Controller.Request.ForgetPasswordRequest;
import com.Notes_Sharing.Controller.Request.LoginRequest;
import com.Notes_Sharing.Controller.Request.RegistrationRequest;
import com.Notes_Sharing.Controller.Response.UserResponse;
import com.Notes_Sharing.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public UserResponse createUser(@Valid @RequestBody RegistrationRequest user) {

		if (userService.UserExists(user.getEmail())) {
		return new  UserResponse("User Already Exists",false);
		}else {
			userService.createUser(user);
			return new  UserResponse("User Registeration Successfully",true);
		}
	}

	@PostMapping("/login")
	public UserResponse loginUser( @ Valid@RequestBody LoginRequest loginRequest) {
		return userService.LoginUser(loginRequest);
	}

	@PostMapping("/forgetpass")
	public UserResponse ResetPassword(@Valid @ RequestBody ForgetPasswordRequest fPasswordRequest) {
		return userService.forgetPassword(fPasswordRequest);

	}
}
