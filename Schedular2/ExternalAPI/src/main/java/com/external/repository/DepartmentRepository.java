package com.external.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.external.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
