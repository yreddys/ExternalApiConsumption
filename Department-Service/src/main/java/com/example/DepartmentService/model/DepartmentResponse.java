package com.example.DepartmentService.model;

import java.time.LocalDateTime;

public class DepartmentResponse {
	private int dId;
	private String dName;
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
	public DepartmentResponse(int dId, String dName, LocalDateTime createdAt) {
		
		this.dId = dId;
		this.dName = dName;
		this.createdAt = createdAt;
	}
	public DepartmentResponse() {
		
	}
	@Override
	public String toString() {
		return "DepartmentResponse [dId=" + dId + ", dName=" + dName + ", createdAt=" + createdAt + "]";
	}
	
	
}
