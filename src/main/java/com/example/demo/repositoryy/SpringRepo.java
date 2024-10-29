package com.example.demo.repositoryy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SpringModel;

@Repository
public interface SpringRepo extends JpaRepository<SpringModel, Integer> {

}
