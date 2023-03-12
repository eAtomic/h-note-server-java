package cn.hyv5.hnote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.hyv5.hnote.mapper")
public class HNoteServerJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HNoteServerJavaApplication.class, args);
    }

}
