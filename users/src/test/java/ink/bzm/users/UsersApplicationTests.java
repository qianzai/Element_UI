package ink.bzm.users;

import ink.bzm.users.pojo.User;
import ink.bzm.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
class UsersApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testFindAll() {
        userService.findAll().forEach(user -> System.out.println("user = " + user));
    }

    @Test
    void testSave() {
        User user = new User();
        user.setName("三千千");
        user.setBir(new Date());
        user.setAddress("上海市");
        user.setSex("男");
        userService.save(user);
    }

    @Test
    void testDelete() {
        userService.delete("16");
    }

}
