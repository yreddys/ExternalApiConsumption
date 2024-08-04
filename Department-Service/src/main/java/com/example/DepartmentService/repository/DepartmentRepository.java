package com.example.DepartmentService.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DepartmentService.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{

	List<Department> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
