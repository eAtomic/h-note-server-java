package cn.hyv5.hnote.service;

import cn.hyv5.hnote.entity.po.Permission;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PermissionServiceTest {
    @Resource
    private PermissionService service;
    @Test
    void save_1() {
        var permission = new Permission();
        permission.setPermissionName("USER:LIST:READ");
        permission.setDescription("读取用户列表");
        service.save(permission);
    }
    @Test
    void save_2() {
        var permission = new Permission();
        permission.setPermissionName("USER:USER:CREATE");
        permission.setDescription("创建新用户");
        service.save(permission);
    }
    @Test
    void save_3() {
        var permission = new Permission();
        permission.setPermissionName("USER:INFO:READ");
        permission.setDescription("获取用户简要介绍");
        service.save(permission);
    }
    @Test
    void save_4() {
        var permission = new Permission();
        permission.setPermissionName("USER:DETAIL:READ");
        permission.setDescription("获取用户详细介绍");
        service.save(permission);
    }
    @Test
    void save_5() {
        var permission = new Permission();
        permission.setPermissionName("USER:DETAIL:UPDATE");
        permission.setDescription("修改用户详细介绍");
        service.save(permission);
    }
}