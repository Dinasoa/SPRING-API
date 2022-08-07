package com.example.schoolapi.repository;

import com.example.schoolapi.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepoJPAImpl extends JpaRepository<Students , Long> {
}
