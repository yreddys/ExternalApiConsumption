package com.example.DepartmentService.controller;



import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DepartmentService.service.PdfServiceForEach;
import com.itextpdf.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfControllerForEach {

    @Autowired
    private PdfServiceForEach pdfServiceForEach;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateReports(
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime start = LocalDateTime.parse(startTime, formatter);
            LocalDateTime end = LocalDateTime.parse(endTime, formatter);

            List<byte[]> pdfReports = pdfServiceForEach.generateSeparateReports(start, end);

            // Zip the PDF reports
            byte[] zipFile = zipByteArrays(pdfReports);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "reports.zip");

            return new ResponseEntity<>(zipFile, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error generating reports: " + e.getMessage()).getBytes());
        }
    }

    private byte[] zipByteArrays(List<byte[]> byteArrayList) throws IOException, java.io.IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
            for (int i = 0; i < byteArrayList.size(); i++) {
                byte[] byteArray = byteArrayList.get(i);
                ZipEntry zipEntry = new ZipEntry("report_" + (i + 1) + ".pdf");
                zipOut.putNextEntry(zipEntry);
                zipOut.write(byteArray);
                zipOut.closeEntry();
            }
        }
        return baos.toByteArray();
    }
}
