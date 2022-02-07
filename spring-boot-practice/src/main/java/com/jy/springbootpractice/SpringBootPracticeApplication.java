package com.jy.springbootpractice;

import com.jy.springbootpractice.properties.MyProperties;
import com.jy.springbootpractice.service.StudentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

import lombok.RequiredArgsConstructor;

// @EnableCaching // 캐시 활성화
// 스프링 캐시는 스프링 컨테이너의 모든 싱글톤 빈이 인스턴스화 된 이후에 동작하게끔 되어있으므로,`@PostConstruct` 시점에서
// 동작을 보장할 수 없음
// 스프링 부트 startup 이 끝난 직후 실행되는 다른 로직이 필요이에 `@EventListner` +
// `ApplicationReadyEvent` 를 이용해 해결
@RequiredArgsConstructor
@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPracticeApplication.class, args);
    }

    private final StudentService studentService;

    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 준비가 끝났을 때, 모든 빈을 다 읽고 스프링컨테이너가 준비가 되었 때
    public void init() {
        studentService.printStudent("jack");
        studentService.printStudent("jack");
        studentService.printStudent("jack");
        studentService.printStudent("fred");
        studentService.printStudent("cassie");
        studentService.printStudent("cassie");
    }
}
