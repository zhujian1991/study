package cn.zhu.test.mapper;


import cn.zhu.test.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface UserMapper {
    //    @Select("select * from user t_user name = #{name}")
    List<User> likeName(String name);

    //    @Select("select * from user where id = #{id}")
    User getById(long id);

    //    @Select("select name from user where id = #{id}")
    String getNameById(long id);

    HashMap  allUser();

    void add(Long id, String name);

    void insert(User user1);
}