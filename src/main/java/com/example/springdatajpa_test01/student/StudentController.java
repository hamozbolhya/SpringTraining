package com.example.springdatajpa_test01.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudent();
    }
    @PostMapping
    public void  addNewStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudentById(id);
    }
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String lastname, @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, lastname,  email);
    }
}
