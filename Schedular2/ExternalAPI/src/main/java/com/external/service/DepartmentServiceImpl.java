package com.external.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.external.entity.Department;
import com.external.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private RestTemplate restTemplate;

	public DepartmentServiceImpl(RestTemplate restTemplate, DepartmentRepository departmentRepository) {
		this.restTemplate = restTemplate;
		this.departmentRepository = departmentRepository;
	}

	@Scheduled(cron = "0 */5 * * * ?") // This cron expression schedules the task to run every 5 minutes
	public void fetchAndSaveDepartments() {
		String apiUrl = "http://externalapi-env.eba-qpsmwnpt.ap-south-1.elasticbeanstalk.com/getAll";
		Department[] departments = restTemplate.getForObject(apiUrl, Department[].class);
		System.out.println(departments);
		if (departments != null) {
			departmentRepository.saveAll(Arrays.asList(departments));
		}
	}
}
