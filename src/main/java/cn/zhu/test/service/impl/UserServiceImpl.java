package cn.zhu.test.service.impl;

import cn.zhu.test.domain.User;
import cn.zhu.test.mapper.UserMapper;
import cn.zhu.test.service.UserService;
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
    public User get(Long id) {
        User byId = userMapper.getById(id);
        return byId;
    }

    @Override
    public List<User> likeName(String name) {
        List<User> list = userMapper.likeName(name);
        return list;
    }

    @Override
    public HashMap allUser() {
        HashMap hashMap = userMapper.allUser();
        return hashMap;
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public void add(Long id, String name) {
        userMapper.add(id,name);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public void insert(User user1) {
        userMapper.insert(user1);
    }

}
