package com.saber.sample.client.springboot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String field;
}