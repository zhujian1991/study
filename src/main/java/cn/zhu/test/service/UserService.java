package cn.zhu.test.service;
import cn.zhu.test.domain.User;
import java.util.HashMap;
import java.util.List;

/**
 *  @author 程序老仁
 */
public interface UserService {
    /**
     * 得到对象
     */
    User get(Long Id);
    /**
     * 查询对象
     */
    List<User> likeName(String name);
    /**
     * 添加对象
     */
    HashMap allUser();
    /**
     * 添加对象
     * @author 程序老仁
     */
    void add(Long id, String name);
    /**
     * 插入对象
     * @author 程序老仁
     */
    void insert(User user1);
}
