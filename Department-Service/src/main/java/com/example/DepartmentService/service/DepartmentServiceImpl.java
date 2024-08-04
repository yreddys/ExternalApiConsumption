package com.example.DepartmentService.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DepartmentService.entity.Department;
import com.example.DepartmentService.exception.DepartmentNotFoundException;
import com.example.DepartmentService.model.DepartmentRequest;
import com.example.DepartmentService.model.DepartmentResponse;
import com.example.DepartmentService.repository.DepartmentRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentRepository repo;

	@Autowired
	DepartmentServiceImpl(DepartmentRepository repo) {
		this.repo = repo;
	}

	@Override
	public DepartmentRequest savingDepartment(DepartmentRequest departmentRequest) {
		log.info("Saving department {}", departmentRequest);

		// Create a new department and set its name
		Department department = new Department();
		department.setdName(departmentRequest.getdName());
		department.setCreatedAt(null);
		// magic will happens if we keep above line after save method
		// Save the department
		Department savedDepartment = repo.save(department);

		log.info("Saved department {}", savedDepartment);

		// Create a response object and set its name based on the saved department
		DepartmentRequest deptResponse = new DepartmentRequest();
		deptResponse.setdName(savedDepartment.getdName());

		return deptResponse;
	}

	@Override
	public DepartmentResponse getDepartmentById(int dId) {
		log.info("Fetching department by ID: {}", dId);

		Optional<Department> departmentOptional = repo.findById(dId);

		if (departmentOptional.isPresent()) {
			Department department = departmentOptional.get();
			DepartmentResponse deptResponse = new DepartmentResponse();
			deptResponse.setdId(department.getdId());
			deptResponse.setdName(department.getdName());
			log.info("Department found: {}", department);
			return deptResponse;
		} else {
			String errorMessage = "Department with ID " + dId + " not found. Deletion aborted.";
			log.warn(errorMessage);
			throw new DepartmentNotFoundException(errorMessage);
		}
	}

	@Override
	public DepartmentResponse updateDepartmentById(int dId, DepartmentRequest departmentRequest) {
		Optional<Department> optionalDepartment = repo.findById(dId);

		if (optionalDepartment.isPresent()) {
			Department existingDepartment = optionalDepartment.get();

			// Assuming you have appropriate setters in the Department class
			existingDepartment.setdName(departmentRequest.getdName());

			// Save the updated department
			Department updatedDepartment = repo.save(existingDepartment);

			// Create and return a response object based on the updated department
			DepartmentResponse deptResponse = new DepartmentResponse();
			deptResponse.setdId(updatedDepartment.getdId());
			deptResponse.setdName(updatedDepartment.getdName());
			log.info("updated Department : {}", deptResponse);
			return deptResponse;
		} else {
			String errorMessage = "Department with ID " + dId + " not found. Deletion aborted.";
			log.warn(errorMessage);
			throw new DepartmentNotFoundException(errorMessage);
		}
	}

	@Override
	public void deleteDepartmentById(int dId) {
		Optional<Department> optionalDepartment = repo.findById(dId);
		if (optionalDepartment.isPresent()) {
			Department existingDepartment = optionalDepartment.get();
			repo.delete(existingDepartment);
			log.info("Department with ID {} deleted successfully.", dId);

		} else {
			String errorMessage = "Department with ID " + dId + " not found. Deletion aborted.";
			log.warn(errorMessage);
			throw new DepartmentNotFoundException(errorMessage);
		}
	}

	@Override
	public List<DepartmentResponse> getAllData() {
	    List<Department> departments = repo.findAll();

	    if (departments == null) {
	        return Collections.emptyList(); // Return an empty list if no departments are found
	    }

	    return departments.stream().map(department -> {
	        DepartmentResponse deptResponse = new DepartmentResponse();
	        deptResponse.setdId(department.getdId());
	        deptResponse.setdName(department.getdName());
	        return deptResponse;
	    }).collect(Collectors.toList());
	}

}
