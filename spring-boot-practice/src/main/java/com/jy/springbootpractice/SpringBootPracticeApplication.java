package com.jy.springbootpractice;

import com.jy.springbootpractice.properties.MyProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootPracticeApplication {

//    기본사용방법
//    @Value("${my.height}")
    private final Integer height;
    private final MyProperties myProperties;

//    final 사용방법
    public SpringBootPracticeApplication(
            @Value("${my.height}") Integer height,
            MyProperties myProperties
    ){
        this.height = height;
        this.myProperties = myProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPracticeApplication.class, args);
    }

//    필드 주입(인스턴스 생성 후 값 주입) 시 height 값이 null
//    Instantiation 끝난 뒤 하도록 @PostConstruct를 사용한다.
    @PostConstruct
    public void printHeight(){
        System.out.println("@[Value] "+ height);
        System.out.println("[ConfigurationProperties]"+myProperties.getHeight());
    }

}
