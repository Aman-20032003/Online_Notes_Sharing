package com.Notes_Sharing.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Notes_Sharing.Repository.NotesRepository;
@Service
public class NotesService {
	@Autowired
	private NotesRepository  notesRepository;

}
