package com.example.DepartmentService.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DepartmentService.service.PdfService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/reports")
public class PdfController {
// Pdf for each all records
	@Autowired
	private PdfService pdfService;

	@GetMapping("/department-report")
	public ResponseEntity<byte[]> generatePdfReport(HttpServletResponse response) {
		try {
			byte[] pdfBytes = pdfService.generateReport();

			// Set the content type to PDF
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=department_report.pdf");

			return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
