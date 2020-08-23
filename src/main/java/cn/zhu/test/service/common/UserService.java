package cn.zhu.test.service.common;

import cn.zhu.test.config.db.ReadOnly;
import cn.zhu.test.entity.UserEntity;

import java.util.HashMap;
import java.util.List;

/**
 *  @author 程序老仁
 */
public interface UserService {
    /**
     * 得到对象
     */
    UserEntity get(Long Id);
    /**
     * 查询对象
     */
    List<UserEntity> likeName(String name);
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
    void insert(UserEntity user1);

    /**
     * 获取所有
     * @author 程序老仁
     */
    @ReadOnly
     List<UserEntity> getAll();

    /**
     * 获取所有
     * @author 程序老仁
     */
    List<UserEntity> getAllW();
}
