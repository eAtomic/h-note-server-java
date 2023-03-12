package cn.hyv5.hnote.service;

import cn.hyv5.hnote.entity.po.Role;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoleServiceTest {
    @Resource
    private RoleService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save_1() {
        var testRole = new Role();
        testRole.setRoleName("USER");
        testRole.setDescription("普通登录用户");
        service.save(testRole);
    }
    @Test
    void save_2() {
        var testRole = new Role();
        testRole.setRoleName("ADMIN");
        testRole.setDescription("管理员");
        service.save(testRole);
    }
}