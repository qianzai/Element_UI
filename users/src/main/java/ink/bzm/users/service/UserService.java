package ink.bzm.users.service;

import ink.bzm.users.pojo.User;

import java.util.List;

/**
 * @author: buzhengmiao
 * @time: 2020/7/24 10:21
 */
public interface UserService {

    List<User> findAll();

    void save(User user);

    void delete(String id);

    void update(User user);
}
