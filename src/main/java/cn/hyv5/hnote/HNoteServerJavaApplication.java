package cn.hyv5.hnote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("cn.hyv5.hnote.mapper")
@Import(cn.hutool.extra.spring.SpringUtil.class)
public class HNoteServerJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HNoteServerJavaApplication.class, args);
    }

}
