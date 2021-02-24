package ink.bzm.users.dao;

import ink.bzm.users.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: buzhengmiao    docs.bzm.ink
 * @time: 2020/7/24 10:04
 */
@Mapper
public interface UserDAO {

    List<User> findAll();

    void save(User user);

    void delete(String id);

    void update(User user);
}
