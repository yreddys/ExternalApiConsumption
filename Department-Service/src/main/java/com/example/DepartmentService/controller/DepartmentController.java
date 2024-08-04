package com.example.DepartmentService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DepartmentService.model.DepartmentRequest;
import com.example.DepartmentService.model.DepartmentResponse;
import com.example.DepartmentService.service.DepartmentService;
import com.example.DepartmentService.service.PdfService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/dept")
public class DepartmentController {
	private DepartmentService service;

	@Autowired
	DepartmentController(DepartmentService service) {
		this.service = service;
	}

	@Autowired
	private PdfService pdfService;

	@PostMapping("/save")
	String saveDepartment(@RequestBody DepartmentRequest departmentRequest) {
		log.info("Saving department {}", departmentRequest);
		service.savingDepartment(departmentRequest);
		return "Department saved Successfully";
	}

	@GetMapping("/{dId}")
	DepartmentResponse getDepartmentById(@PathVariable int dId) {
		log.info("Requested Department is {}", dId);
		return service.getDepartmentById(dId);
	}

	@PutMapping("/{dId}")
	DepartmentResponse updateDepartmentById(@PathVariable int dId, @RequestBody DepartmentRequest departmentRequest) {
		log.info("Requested Department is {}", dId);
		return service.updateDepartmentById(dId, departmentRequest);
	}

	@DeleteMapping("/{dId}")
	String deleteDepartmentById(@PathVariable int dId) {
		log.info("Requested Department is {}", dId);
		service.deleteDepartmentById(dId);
		return "Deeleted Successfully";
	}

//	@GetMapping("/allData")
//	ResponseEntity<List<DepartmentResponse>> getAllData() {
//		List<DepartmentResponse> departments = service.getAllData();
//		return new ResponseEntity<>(departments, HttpStatus.OK);
//	}
	
	
	// To fetch Pdf Report

	@GetMapping("/PDFReport")
	ResponseEntity<List<DepartmentResponse>> getAllData() {
		List<DepartmentResponse> departments = service.getAllData();
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
}