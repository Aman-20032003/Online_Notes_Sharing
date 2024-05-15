package com.Notes_Sharing.Repository.Entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noteId;
	@NotEmpty(message = "Title  Must Not Be Empty")
	private String title;
	@NotEmpty(message = "Description Must Not Be Empty")
	private String description;
	private String file_Path;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	@ManyToOne
	Subject subject;
}
