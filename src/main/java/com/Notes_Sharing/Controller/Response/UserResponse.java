package com.Notes_Sharing.Controller.Response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserResponse {
	private String message;
	private boolean success;

}
