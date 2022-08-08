package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepostiory studentRepostiory;

    @Autowired
    public StudentService(StudentRepostiory studentRepostiory) {
        this.studentRepostiory = studentRepostiory;
    }

    public List<Student> getStudent(){
        return studentRepostiory.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepostiory.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }

        studentRepostiory.save(student);
    }
}
