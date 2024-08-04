package com.schedular.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PdfFetchScheduler {

    @Autowired
    private RestTemplate restTemplate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Scheduled(cron = "0 */5 * * * *") // Executes every 5 minutes
    public void fetchDataFromEndpoint() {
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusHours(24);

        String url = String.format("http://localhost:8080/pdf/generate?startTime=%s&endTime=%s",
                startTime.format(formatter), endTime.format(formatter));

        System.out.println("Scheduled task started. Fetching data from endpoint: " + url);

        try {
            System.out.println("Calling endpoint: " + url);

            // Call the endpoint to get the zipped PDF data
            ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);

            // Log response status code
            System.out.println("Received response with status code: " + response.getStatusCode());

            // Check if the response is successful
            if (response.getStatusCode().is2xxSuccessful()) {
                byte[] zipData = response.getBody();

                // Log size of received data
                System.out.println("Received ZIP data size: " + (zipData != null ? zipData.length : 0) + " bytes");

                // Check if the ZIP data is not null or empty
                if (zipData != null && zipData.length > 0) {
                    // Save the ZIP data to a file
                    String fileName = "downloaded_reports_" + System.currentTimeMillis() + ".zip";
                    System.out.println("Saving ZIP file to: " + fileName);
                    saveZipToFile(zipData, fileName);
                } else {
                    System.err.println("Received ZIP data is empty.");
                }
            } else {
                System.err.println("Failed to fetch data. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Exception occurred while fetching data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveZipToFile(byte[] zipData, String fileName) {
        try (OutputStream os = new FileOutputStream(fileName)) {
            os.write(zipData);
            System.out.println("ZIP file saved successfully to: " + fileName);
        } catch (IOException e) {
            System.err.println("IOException occurred while saving ZIP file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
