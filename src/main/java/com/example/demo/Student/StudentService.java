package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    public void deleteStudent(Long studentId) {
        boolean existsByIdStudent = studentRepostiory.existsById(studentId);
        if(!existsByIdStudent){
            throw new IllegalStateException("Student with id " + studentId + " dose not exists");
        }
        studentRepostiory.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepostiory.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " dose not exists"));
        if(name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepostiory.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }

            student.setEmail(email);
        }
    }
}
