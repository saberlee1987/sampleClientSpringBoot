package com.saber.sample.client.springboot.controllers;

import com.saber.sample.client.springboot.dto.StudentModel;
import com.saber.sample.client.springboot.model.Student;
import com.saber.sample.client.springboot.services.StudentService;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${service.api.basePath}")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody StudentModel studentModel){
        Student student=this.studentService.addStudent(studentModel);
        return ResponseEntity.ok(student);
    }
    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Student>> findAll(){
        List<Student> students =this.studentService.findAll();
        return ResponseEntity.ok(students);
    }
}
