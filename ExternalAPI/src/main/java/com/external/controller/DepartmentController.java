package com.external.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.external.service.DepartmentService;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/fetch-and-save-departments")
    public String fetchAndSaveDepartments() {
        departmentService.fetchAndSaveDepartments();
        return "Departments fetched and saved successfully.";
    }
}
