package com.Notes_Sharing.Controller.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteUploadRequest {
	private String title;
	private String subject;
	private String description;
	private String file1;
	private String file2;
	private String file3;
	private String file4;
}
