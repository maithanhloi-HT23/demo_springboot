package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
