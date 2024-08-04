package com.example.DepartmentService.service;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DepartmentService.entity.Department;
import com.example.DepartmentService.repository.DepartmentRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

@Service
public class PdfServiceForEach {

    @Autowired
    private DepartmentRepository repo;

    // Generate PDF for each department and return them as a list of byte arrays
    public List<byte[]> generateSeparateReports() {
        List<Department> departments = repo.findAll();
        return departments.stream().map(this::generateSingleDepartmentReport).collect(Collectors.toList());
    }

    // Generate PDF for each department within the given time range and return them as a list of byte arrays
    public List<byte[]> generateSeparateReports(LocalDateTime start, LocalDateTime end) {
        List<Department> departments = repo.findByCreatedAtBetween(start, end);
        return departments.stream().map(this::generateSingleDepartmentReport).collect(Collectors.toList());
    }

    // Generate a single PDF for one department
    private byte[] generateSingleDepartmentReport(Department department) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        try {
            Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2}));
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
