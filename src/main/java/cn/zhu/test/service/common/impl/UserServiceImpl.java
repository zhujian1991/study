package cn.zhu.test.service.common.impl;

import cn.zhu.test.config.db.ReadOnly;
import cn.zhu.test.entity.UserEntity;
import cn.zhu.test.mapper.UserMapper;
import cn.zhu.test.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
     private UserMapper userMapper ;
    @Override
    public UserEntity get(Long id) {
        UserEntity byId = userMapper.getById(id);
        return byId;
    }

    @Override
    public List<UserEntity> likeName(String name) {
        List<UserEntity> list = userMapper.likeName(name);
        return list;
    }

    @Override
    public HashMap allUser() {
        HashMap hashMap = userMapper.allUser();
        return hashMap;
    }


    /**
     * 测试读取，应该使用读库
     */
    @ReadOnly
    public List<UserEntity> getAll() {
        return userMapper.getAll();
    }

    @Override
    public List<UserEntity> getAllW() {
        return userMapper.getAllW();
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public void add(Long id, String name) {
        userMapper.add(id,name);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public void insert(UserEntity user1) {
        userMapper.insert(user1);
    }

}
