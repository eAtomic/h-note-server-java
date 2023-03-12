package cn.hyv5.hnote.service;

import cn.hyv5.hnote.entity.po.UserRole;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRoleServiceTest {
    @Resource
    private UserRoleService service;
    @Test
    void save() {
        var ur = new UserRole();
        ur.setExpire(LocalDateTime.of(2025,5,1,0,0));
        ur.setUserId("1634761173308448769"); //Admin
//        ur.setRoleId("1634891847747125249"); //ADMIN
        ur.setRoleId("1634891348071358465"); // USER
        service.save(ur);
    }
}