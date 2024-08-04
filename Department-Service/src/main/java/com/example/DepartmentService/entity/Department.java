package com.example.DepartmentService.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class Department {
	@Id
	@GeneratedValue
	private int dId;
	private String dName;
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Department(int dId, String dName, LocalDateTime createdAt) {
		
		this.dId = dId;
		this.dName = dName;
		this.createdAt = createdAt;
	}
	public Department() {
		
	}
	@Override
	public String toString() {
		return "Department [dId=" + dId + ", dName=" + dName + ", createdAt=" + createdAt + "]";
	}

	
}