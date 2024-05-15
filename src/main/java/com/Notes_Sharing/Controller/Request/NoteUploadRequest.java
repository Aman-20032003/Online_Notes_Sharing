package com.Notes_Sharing.Controller.Request;


import lombok.Data;

@Data

public class NoteUploadRequest {
	private String title;
	private String subject;
	private String description;
}
