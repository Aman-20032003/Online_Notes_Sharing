package com.Notes_Sharing.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Notes_Sharing.Controller.Request.SubjectRequest;
import com.Notes_Sharing.Controller.Response.SubjectResponse;
import com.Notes_Sharing.Service.SubjectService;

import jakarta.validation.Valid;
@RestController
public class SubjectController {
	@Autowired
	private SubjectService service;
	@PostMapping("/subject")
	public SubjectResponse saveSubject(@RequestBody @Valid SubjectRequest subjectRequest) {
		if (subjectRequest.getSName().equals(null)){
			 return new SubjectResponse("Subject Name Required",false);
		}
		 service.saveSubject(subjectRequest);
		 return new SubjectResponse("Subject Saved Successfully", true);
		}
	}
	


