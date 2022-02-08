package com.jy.springbootpractice.repository;

import java.util.Map;

import com.jy.springbootpractice.domain.Student;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.ProxyBits;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
// @Repository
// // Spring Native
@Component
// @AotProxyHint(targetClass =
// com.jy.springbootpractice.repository.StudentRepository.class, proxyFeatures =
// ProxyBits.IS_STATIC)
public class StudentRepository {

    private final Map<String, Student> storage;

    @Cacheable("student")
    public Student getStudent(String name) {
        System.out.println("[repo] 나의 통행료는 무척 비싸다!");
        return storage.get(name);
    }

    public Student enroll(String name, Integer age, Student.Grade grade) {
        return storage.put(name, Student.of(name, age, grade));
    }
}
