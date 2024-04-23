package com.Notes_Sharing.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Notes_Sharing.Controller.Request.SubjectRequest;

import com.Notes_Sharing.Repository.SubjectRepository;
import com.Notes_Sharing.Repository.Entity.Subject;

@Service
public class SubjectService {
@Autowired
private SubjectRepository subjectRepository;

public Subject saveSubject( SubjectRequest subjectRequest) {
Subject subject= Subject.builder().sName(subjectRequest.getSName()).build();
return subjectRepository.save(subject);
}
}
