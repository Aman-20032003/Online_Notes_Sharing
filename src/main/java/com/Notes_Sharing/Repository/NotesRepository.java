package com.Notes_Sharing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Notes_Sharing.Controller.Response.NotesResponse;
import com.Notes_Sharing.Repository.Entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

}
