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
	private int id;
	private String name;
	private int ctc;
}
