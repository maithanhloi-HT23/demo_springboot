package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepostiory repostiory){
        return args -> {
            Student mai_thanh_loi = new Student(
                    "Mai Thanh Loi",
                    "maithanhloi.hvACT@gmail.com",
                    LocalDate.of(2001, DECEMBER, 7),
                    21
            );
            Student mai_thanh_thang = new Student(
                    "Mai Thanh Thang",
                    "maithangthien.hvACT@gmail.com",
                    LocalDate.of(2001, DECEMBER, 7),
                    21
            );

            repostiory.saveAll(
                    List.of(mai_thanh_loi, mai_thanh_thang)
            );
        };
    }

}
