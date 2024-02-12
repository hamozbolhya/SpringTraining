package com.example.springdatajpa_test01.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
      if(studentOptional.isPresent()) {
          throw  new IllegalStateException("Email taken");
      } else {
          studentRepository.save(student);
      }
    }
    @Transactional
    public void updateStudent(Long studentId, String name,String lastname, String email) {
       Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
               "student with id " + studentId + "does not exist"
       ));

       if(name != null && !name.isEmpty() && !Objects.equals(student.getFirstName(), name)) {
           student.setFirstName(name);
       }

        if(lastname != null && !lastname.isEmpty() && !Objects.equals(student.getLastName(), lastname)) {
            student.setLastName(lastname);
        }

       if(email!= null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
           Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
           if(studentOptional.isPresent()) {
               throw new IllegalStateException("email taken");
           }
           student.setEmail(email);
       }
    }
    public void deleteStudentById(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("The student that you try to delete does not exist");
        }
        studentRepository.deleteById(id);
    }
}
