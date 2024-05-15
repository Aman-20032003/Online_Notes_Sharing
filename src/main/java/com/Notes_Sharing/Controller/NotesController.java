package com.Notes_Sharing.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Notes_Sharing.Controller.Request.NoteUploadRequest;
import com.Notes_Sharing.Controller.Request.SubjectRequest;
import com.Notes_Sharing.Controller.Response.NotesResponse;

import com.Notes_Sharing.Service.NotesService;

@RestController
public class NotesController {

	@Autowired
	private NotesService notesService;
	
	@Value("${file.upload.directory}")
	private String uploadDirectory;
	private static final Logger logger = Logger.getLogger(NotesService.class.getName());

	@PostMapping("/fileupload/{email}")
	public NotesResponse uploadFiles(@RequestParam("file") List<MultipartFile> files,
			NoteUploadRequest noteUploadRequest, SubjectRequest subjectRequest, @PathVariable String email)
			throws IOException {
		 for (MultipartFile file : files) {
		        Path filePath = Paths.get(uploadDirectory, file.getOriginalFilename());
		        if (Files.exists(filePath)) {
		            logger.warning("File with the same name already exists: " + file.getOriginalFilename());
		            return new NotesResponse("File with the same name already exists: " + file.getOriginalFilename(), false);
		        }

		        if (!file.getOriginalFilename().contains(".pdf") || file.isEmpty()) {
		            logger.warning("Only Pdf Files Allowed Or Select Atleast One File");
		            return new NotesResponse("Only Pdf Files Are Allowed or No File Selected", false);
		        }

		    }
		 return  notesService.uploadFiles(email, files, noteUploadRequest, subjectRequest);

		}

	@GetMapping("/download/{filename}")
	public Path download(@PathVariable String filename) throws IOException {
		return notesService.downloadFiles(filename);

	}
	@DeleteMapping("/delete/{email}/{filename}")
	public NotesResponse deleteNotes(@PathVariable String  email, @PathVariable String  filename) {
		return notesService.deleteNotes(email, filename);
		
	}
	@GetMapping("/subjectCounter/{email}")
	public int subjectCounter( @PathVariable String  email) {
	return notesService.getSubjectCounter(email);
		}
	@GetMapping("/NoteCounter/{email}")
	public int NotesCounter( @PathVariable String  email) {
	return notesService.getNotesCounter(email);
		}
}
