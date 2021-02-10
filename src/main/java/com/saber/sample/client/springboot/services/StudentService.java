package com.saber.sample.client.springboot.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.saber.sample.client.springboot.dto.StudentModel;
import com.saber.sample.client.springboot.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private Keycloak keycloakClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${service.student.baseUrl}")
    private String studentBaseUrl;
    @Value(value = "${service.student.port}")
    private String port;
    @Value(value = "${service.student.basePath}")
    private String basePath;
    @Value(value = "${service.student.addStudent}")
    private String addStudentUrl;
    @Value(value = "${service.student.findAll}")
    private String findAllUrl;


    public Student addStudent(StudentModel studentModel) {
        HttpHeaders httpHeaders = getHeaders();
        log.info("Request for add Student == > {}",studentModel);
        HttpEntity<StudentModel> httpEntity = new HttpEntity<>(studentModel,httpHeaders);
        String url = studentBaseUrl + ":" + port + basePath + addStudentUrl;
        ResponseEntity<Student> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Student.class);
        log.info("Response From Service Add Student ====> {} ",responseEntity.getBody());
        log.info("Response From Service With StatusCode ====> {} ",responseEntity.getStatusCode());

        return responseEntity.getBody();

    }

    public List<Student> findAll(){
        HttpHeaders httpHeaders = getHeaders();
        HttpEntity httpEntity= new HttpEntity(httpHeaders);
        log.info("Request for Find All Student ");
        String url = studentBaseUrl + ":" + port + basePath + findAllUrl;
        ParameterizedTypeReference<List<Student>> parameterizedTypeReference =new ParameterizedTypeReference<List<Student>>() {};
        ResponseEntity<List<Student>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, parameterizedTypeReference);
        log.info("Response From Service Add Student ====> {} ",responseEntity.getBody());
        log.info("Response From Service With StatusCode ====> {} ",responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String token =  keycloakClient.tokenManager().getAccessToken().getToken();
        log.info("Received Token from keycloak ====> {} ",token);
        httpHeaders.add("Authorization", String.format("Bearer %s",token));
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }
}
