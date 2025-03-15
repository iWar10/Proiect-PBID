package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Trial;

public interface TrialRepository extends JpaRepository<Trial, Long>{

}
