package com.example.DepartmentService.service;

import java.util.List;

import com.example.DepartmentService.model.DepartmentRequest;
import com.example.DepartmentService.model.DepartmentResponse;

public interface DepartmentService {

	DepartmentResponse getDepartmentById(int dId);

	DepartmentRequest savingDepartment(DepartmentRequest departmentRequest);

	DepartmentResponse updateDepartmentById(int dId, DepartmentRequest departmentRequest);

	void deleteDepartmentById(int dId);

	List<DepartmentResponse> getAllData();

	

}
