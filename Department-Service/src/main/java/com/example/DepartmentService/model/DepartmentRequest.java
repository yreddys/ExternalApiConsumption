package com.example.DepartmentService.model;

public class DepartmentRequest {
	
	private String dName;

	

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public DepartmentRequest( String dName) {

		this.dName = dName;
	}

	public DepartmentRequest() {
	}

	@Override
	public String toString() {
		return "Department [dName=" + dName + "]";
	}

}
