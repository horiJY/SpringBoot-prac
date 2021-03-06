package com.jy.springbootpractice.service;

import javax.annotation.PostConstruct;

import com.jy.springbootpractice.domain.Student;
import com.jy.springbootpractice.repository.StudentRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public void printStudent(String name) {
        Student student = studentRepository.getStudent(name);
        System.out.println("μ°Ύλ νμ: " + student);
    }

    @PostConstruct
    public void init() {
        studentRepository.enroll("jack", 15, Student.Grade.B);
        studentRepository.enroll("cassie", 17, Student.Grade.A);
        studentRepository.enroll("fred", 13, Student.Grade.F);
    }
}