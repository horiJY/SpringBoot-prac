package com.jy.springbootpractice.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Configuration;

//@Configuration
@ConfigurationProperties("my") // ConfigurationProperties만 쓰면 빈 스캐닝 등록 안되었다는 에러발생
@ConstructorBinding
public class MyProperties {
    private final Integer height;

    public MyProperties(@Value("${my.height") Integer height) {
        this.height = height;
    }
    public Integer getHeight() {
        return height;
    }

//    public void setHeight(Integer height) {
//        this.height = height;
//    }
}
