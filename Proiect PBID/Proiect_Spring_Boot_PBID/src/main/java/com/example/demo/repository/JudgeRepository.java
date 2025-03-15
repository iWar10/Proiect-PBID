package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Judge;

public interface JudgeRepository extends JpaRepository<Judge, Long>{

}