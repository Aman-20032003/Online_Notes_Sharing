package com.Notes_Sharing.Repository.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.AssertFalse.List;
import lombok.Builder;
import lombok.Data;
@Entity
@Data
@Builder
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sId;
	@NotEmpty(message="Subject Name Required")
	private String sName;
	

}
