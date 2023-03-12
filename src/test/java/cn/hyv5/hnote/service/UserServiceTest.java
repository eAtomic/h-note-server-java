package cn.hyv5.hnote.service;

import cn.hyv5.hnote.entity.enums.EntityStatusType;
import cn.hyv5.hnote.entity.enums.UserVerifyCodeType;
import cn.hyv5.hnote.entity.enums.UserVipType;
import cn.hyv5.hnote.entity.po.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private UserService service;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void getOne() {
    }

    @Test
    void removeById() {
    }

    @Test
    void removeByIds() {
    }

    @Test
    void save() {
        User testUser = new User();
        testUser.setUsername("test");
        testUser.setNickname("测试用户");
        testUser.setPassword("123456");
        testUser.setSalt("111111");
        testUser.setAvatar("");
        testUser.setAvatarWrapper("");
        testUser.setVerifyCd(UserVerifyCodeType.OK);
        testUser.setVipType(UserVipType.ENDLESS_VIP);
        testUser.setStatus(EntityStatusType.NORMAL);
        boolean res = testUser.insert();
        assertTrue(res);
    }

    @Test
    void updateById() {
    }

    @Test
    void getById() {
    }

    @Test
    void testGetOne() {
    }

    @Test
    void count() {
        long count = service.count();
        assertEquals(0, count);
    }

    @Test
    void list() {
    }

    @Test
    void testList() {
    }

    @Test
    void page() {
    }

    @Test
    void testPage() {
    }

    @Test
    void lambdaQuery() {
    }

    @Test
    void testLambdaQuery() {
    }

    @Test
    void lambdaUpdate() {
    }

    @Test
    void testSaveOrUpdate() {
    }

    @Test
    void getSimpleUser() {
        var user = service.getSimpleUser(null,"admin");
        log.info(user.toString());

    }
}