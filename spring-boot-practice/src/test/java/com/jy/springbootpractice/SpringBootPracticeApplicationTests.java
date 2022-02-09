package com.jy.springbootpractice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jy.springbootpractice.domain.Student;

@Testcontainers // 테스트 컨테이너 등록
@SpringBootTest
class SpringBootPracticeApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(SpringBootPracticeApplicationTests.class);

    @Container // 꼭 private static final을 써야 함(가이드참고)
    private static final GenericContainer<?> redisContainer = new GenericContainer<>(
            DockerImageName.parse("redis:latest")); // 도커 이미지 버전은 docker hub 참고

    @Autowired
    private ObjectMapper mapper; // jackson serialze 관찰을 위한 매퍼

    @BeforeAll // 테스트 클래스 내 테스트 메서드를 모두 실행시킨 후 딱 한번 수행
    static void setup() { // 컨테이너 내부 로그 정보확인을 위한 로그
        redisContainer.followOutput(new Slf4jLogConsumer(logger));
    }

    // 테스트 컨테이너의 포트는 랜덤포트이다.
    @DynamicPropertySource // configuration poperties를 나중에 읽어서 주입하게 해줌, Spring Framework 5.2.5이상
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.cache.type", () -> "redis");
        registry.add("spring.redis.port", () -> redisContainer.getMappedPort(6379));
        // spring.redis.port properties 값을 redisContainer의 6379포트와 대응되는 값을 가져와 덮어 씌움
    }

    @Test
    void contextLoads() throws Exception {
        // Given

        // When
        // execIncatainer() : 컨테이너 안에서 명령어 수행 띄어쓰기는 , 로 구분
        GenericContainer.ExecResult execResult1 = redisContainer.execInContainer("redis-cli", "get", "student:cassie");
        GenericContainer.ExecResult execResult2 = redisContainer.execInContainer("redis-cli", "get", "student:fred");
        GenericContainer.ExecResult execResult3 = redisContainer.execInContainer("redis-cli", "get", "student:jack");
        System.out.println(execResult1.getStdout());
        System.out.println(execResult2.getStdout());
        System.out.println(execResult3.getStdout());

        // execResult의 값을 표준출력으로 mapper를통해 가져와 Student에 넣는다.
        Student actual1 = mapper.readValue(execResult1.getStdout(), Student.class);
        Student actual2 = mapper.readValue(execResult2.getStdout(), Student.class);
        Student actual3 = mapper.readValue(execResult3.getStdout(), Student.class);

        // Then // assertj
        assertThat(redisContainer.isRunning()).isTrue();
        assertThat(actual1).isEqualTo(Student.of("cassie", 18, Student.Grade.A));
        assertThat(actual2).isEqualTo(Student.of("fred", 14, Student.Grade.C));
        assertThat(actual3).isEqualTo(Student.of("jack", 15, Student.Grade.B));

    }

}
