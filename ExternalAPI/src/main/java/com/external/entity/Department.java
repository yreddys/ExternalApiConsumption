package com.external.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Department {
	@Id
	@GeneratedValue
	private int department_id;
	private String departmentName;
	private String departmentDescription;
	private String departmentCode;
}
