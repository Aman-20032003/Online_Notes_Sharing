package com.Notes_Sharing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Notes_Sharing.Repository.Entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
