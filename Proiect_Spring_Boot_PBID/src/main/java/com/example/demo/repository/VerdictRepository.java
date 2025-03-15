package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Verdict;

public interface VerdictRepository extends JpaRepository<Verdict, Long>{

}