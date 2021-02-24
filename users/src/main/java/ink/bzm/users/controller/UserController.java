package ink.bzm.users.controller;

import com.alibaba.druid.util.StringUtils;
import ink.bzm.users.pojo.User;
import ink.bzm.users.service.UserService;
import ink.bzm.users.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2020/7/24 11:16
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials="true")   //解决跨域，5.0.2后，allowCredentials默认为false了
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/saveOrUpdate")
    Result saveOrUpdate(@RequestBody User user) {
        Result result = new Result();
        try {
            if (StringUtils.isEmpty(user.getId())) {
                //保存操作
                userService.save(user);
                result.setMsg("用户信息保存成功！");
            } else {
                //更新操作
                userService.update(user);
                result.setMsg("用户信息编辑成功！");
            }
        } catch (Exception e) {
            result.setStatus(false);
            result.setMsg("出现错误：保存用户失败,请稍后再试。。。");
        }
        return result;
    }

    @GetMapping("/delete/{id}")
    Result delete(@PathVariable String id) {
        Result result = new Result();
        try {
            userService.delete(id);
            result.setMsg("删除用户信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false);
            result.setMsg("删除用户信息失败,请稍后再试！");
        }

        return result;
    }

}
