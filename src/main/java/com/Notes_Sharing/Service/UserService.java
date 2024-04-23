package com.Notes_Sharing.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Notes_Sharing.Controller.Request.ForgetPasswordRequest;
import com.Notes_Sharing.Controller.Request.LoginRequest;
import com.Notes_Sharing.Controller.Request.RegistrationRequest;
import com.Notes_Sharing.Controller.Response.UserResponse;
import com.Notes_Sharing.Repository.UserRepository;
import com.Notes_Sharing.Repository.Entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(RegistrationRequest userRequest) {
		User user = User.builder().name(userRequest.getName()).mobileNo(userRequest.getMobileNo())
				.email(userRequest.getEmail()).password(userRequest.getPassword()).build();
		return userRepository.save(user);
	}

	public boolean UserExists(String email) {
		return userRepository.findUserByEmail(email) != null;
	}

	public UserResponse LoginUser(LoginRequest loginRequest) {

		User user = userRepository.findUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
		if (user != null && user.getEmail().equals(loginRequest.getEmail())
				&& user.getPassword().equals(loginRequest.getPassword())) {
			return new UserResponse("Login successfully", true);
		} else {
			return new UserResponse("Invalid Email Or Password", false);
		}
	}

	public UserResponse forgetPassword(ForgetPasswordRequest fPasswordRequest) {
		User user = userRepository.findUserByEmail(fPasswordRequest.getEmail());
		if (user == null || !user.getEmail().equals(fPasswordRequest.getEmail())
				&& user.getMobileNo() != (fPasswordRequest.getMobileNo())) {
			return new UserResponse("Invalid Email Or Mobile Number", false);
		}
		if (!fPasswordRequest.getNewPassword().equals(fPasswordRequest.getConfirmPassword())) {
			return new UserResponse("New Password And Confirm Password Does Not Match", false);
		} else {
			user.setPassword(fPasswordRequest.getNewPassword());
			userRepository.save(user);
			return new UserResponse("Password Changed Successfully", true);
		}
	}

}
