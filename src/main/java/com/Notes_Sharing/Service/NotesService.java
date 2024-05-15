package com.Notes_Sharing.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Notes_Sharing.Controller.Request.NoteUploadRequest;
import com.Notes_Sharing.Controller.Request.SubjectRequest;
import com.Notes_Sharing.Controller.Response.NotesResponse;

import com.Notes_Sharing.Repository.NotesRepository;
import com.Notes_Sharing.Repository.SubjectRepository;
import com.Notes_Sharing.Repository.UserRepository;
import com.Notes_Sharing.Repository.Entity.Notes;
import com.Notes_Sharing.Repository.Entity.Subject;
import com.Notes_Sharing.Repository.Entity.User;

@Service
public class NotesService {
	@Autowired
	private NotesRepository notesRepository;
	@Value("${file.upload.directory}")
	private String uploadDirectory;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private UserRepository userRepository;

	private static final Logger logger = Logger.getLogger(NotesService.class.getName());

	public NotesResponse uploadFiles(String email, List<MultipartFile> files, NoteUploadRequest noteUploadRequest,
			SubjectRequest subjectRequest) throws IOException {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			logger.warning("User Not Found: " + user);
			return new NotesResponse("User not found", false);
		} else {
			List<Notes> notesList = new ArrayList<>();
			List<Subject> subjectsList = new ArrayList<>();
			for (MultipartFile file : files) {
				Path directoryPath = Paths.get(uploadDirectory);
				try {
					if (!Files.exists(directoryPath)) {
						Files.createDirectories(directoryPath);
					}
					Path filePath = Paths.get(uploadDirectory, file.getOriginalFilename());
					Files.write(filePath, file.getBytes());

					Notes note = Notes.builder().title(noteUploadRequest.getTitle())
							.description(noteUploadRequest.getDescription()).file_Path(filePath.toString()).user(user)
							.build();
					notesList.add(note);
					notesRepository.save(note);

					Subject subject = Subject.builder().sName(subjectRequest.getSName())
							.note(Collections.singletonList(note)).user(user).build();
					subjectsList.add(subject);
					subjectRepository.save(subject);
					note.setSubject(subject);
					user.setNote(notesList);
					user.setSubject(subjectsList);
					userRepository.save(user);
				}

				catch (IOException e) {
					e.printStackTrace();
					logger.warning(email + " File Not Upload");
					return new NotesResponse("File Not Upload", false);
				}
			}
			logger.info(email + " Upload Files");
			return new NotesResponse("Files Uploaded Successfully", true);
		}
	}

	public Path downloadFiles(String filename) {
		Path filePath = Paths.get(uploadDirectory, filename);
		if (Files.exists(filePath)) {
			logger.info("File path is :" + filePath);
			return filePath;
		}
		logger.warning(filename + " does not exists");
		return null;

	}

	public NotesResponse deleteNotes(String email, String fileName) {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			logger.warning("User Not Found: " + email);
			return new NotesResponse("User not found", false);
		}
		if (!email.equals(user.getEmail())) {
			logger.warning("User Not Found: " + email);
			return new NotesResponse("User not found", false);
		}
		for (Notes note : user.getNote()) {
			if (note.getFile_Path().contains(fileName)) {
				if (note != null) {
					user.getNote().remove(note);
					notesRepository.delete(note);
					userRepository.save(user);
					Subject subject = note.getSubject();
	                if (subject != null) {
	                    user.getSubject().remove(subject);
	                    subjectRepository.delete(subject);
	                    Path filePath = Paths.get(uploadDirectory, fileName);
	                    if (Files.exists(filePath)) {
	                        try {
	                            Files.delete(filePath);
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                            logger.warning("Failed to delete file: " + filePath.toString());
	                            return new NotesResponse("Failed to delete file", false);
	                        }
	                }
					logger.info(user + " File Deleted Successfully");
					return new NotesResponse("File Deleted Successfully", true);
				}
			}

			break;
		}
		logger.warning(fileName + " Not Found");
		return new NotesResponse("File Not Found ", false);
		}
		return null;

	}

	public int getSubjectCounter(String email) {
		User user = userRepository.findUserByEmail(email);
		 Set<String> subjectNames = new HashSet<>();
		    for (Subject subject : user.getSubject()) {
		        subjectNames.add(subject.getSName());
		    }

		    return subjectNames.size();
		    
	}
	public int getNotesCounter(String email) {
		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			return 0;
		}
		if (user.getNote().isEmpty()) {
			return 0;
		}
		int nCounter = 0;
		List<Notes> notes = user.getNote();
		nCounter = notes.size();
		return nCounter;
	}
}
