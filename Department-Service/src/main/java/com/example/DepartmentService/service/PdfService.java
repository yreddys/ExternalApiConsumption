package com.example.DepartmentService.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DepartmentService.entity.Department;
import com.example.DepartmentService.repository.DepartmentRepository;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

@Service
public class PdfService {
// To generated pdf for all records
	
	@Autowired
	private DepartmentRepository repo;

	// To get all department details
	public byte[] generateReport() {
		List<Department> departments = repo.findAll();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(outputStream);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, com.itextpdf.kernel.geom.PageSize.A4.rotate());

		try {
			Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 3 }));
			table.setWidth(UnitValue.createPercentValue(100));
			table.setMarginTop(10);

			Cell cell = new Cell();
			cell.setBackgroundColor(new DeviceGray(0.75f));
			cell.setPadding(5);

			table.addHeaderCell(
					new Cell().add(new Paragraph("Department ID")).setBackgroundColor(new DeviceGray(0.75f)));
			table.addHeaderCell(
					new Cell().add(new Paragraph("Department Name")).setBackgroundColor(new DeviceGray(0.75f)));

			for (Department department : departments) {
				table.addCell(new Cell().add(new Paragraph(String.valueOf(department.getdId()))));
				table.addCell(new Cell().add(new Paragraph(department.getdName())));
			}

			document.add(table);
		} finally {
			document.close();
		}

		return outputStream.toByteArray();
	}

	// Single Department report with vertical format
	public byte[] generateSingleDepartmentReport(Department department) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter writer = new PdfWriter(outputStream);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);

		try {
			Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 2 }));
			table.setWidth(UnitValue.createPercentValue(55));
			table.setMarginTop(10);

			addRow(table, "Department ID", String.valueOf(department.getdId()));
			addRow(table, "Department Name", department.getdName());

			document.add(table);
		} finally {
			document.close();
		}

		return outputStream.toByteArray();
	}

	private void addRow(Table table, String header, String value) {
		Cell headerCell = new Cell().add(new Paragraph(header));
		Cell valueCell = new Cell().add(new Paragraph(value));

		table.addCell(headerCell);
		table.addCell(valueCell);
	}
}
