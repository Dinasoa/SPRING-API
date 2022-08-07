package com.example.schoolapi.repository;

import com.example.schoolapi.model.Students;

import java.util.List;

public interface StudentRepository {
    List<Students> findAll() ;
     Students add(Students s);
     String deleteById(Long id) ;
     Students updateNameById(Long id , String newName);
     List<Students> findWhereNameLike(String query) ;
}
