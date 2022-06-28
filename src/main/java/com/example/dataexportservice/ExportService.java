package com.example.dataexportservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


@Service
public class ExportService {


    private static final String endpoint = "http://localhost:8080/student";
    private final RestTemplate restTemplate;
    private final S3Client s3Client;

    public ExportService(RestTemplate restTemplate, S3Client s3Client) {
        this.restTemplate = restTemplate;
        this.s3Client = s3Client;
    }

    public void getStudent() {
        String url = endpoint;
        String bucketName = "student-data-export-service-bucket";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Student>> result =  restTemplate.exchange(url,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<Student>>(){});
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(Paths.get("/Users/varsha/student.json").toFile(), result);
            String fileName = "student.json";
            String filePath = "/Users/varsha/" + fileName;
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName).key(fileName).build();
            s3Client.putObject(request, RequestBody.fromFile(new File(filePath)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}