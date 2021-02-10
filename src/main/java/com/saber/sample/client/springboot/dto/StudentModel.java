package com.saber.sample.client.springboot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class StudentModel {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String field;
}
