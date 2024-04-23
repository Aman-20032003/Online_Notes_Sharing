package com.Notes_Sharing.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class NotesController {
	@PostMapping("/upload")
	public ResponseEntity<String >uploadFile(@RequestParam("file")MultipartFile file){
		
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No File Selected");
		}
		if(!file.getContentType().equals("application/pdf")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("pdf Files Are Allowed");
		}
		System.out.println(file.getContentType());
		return null;
		
	}
	

}
