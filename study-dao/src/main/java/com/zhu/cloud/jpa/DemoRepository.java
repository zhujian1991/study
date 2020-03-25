package com.zhu.cloud.jpa;


import com.zhu.cloud.entity.User;

/**
 * @author  zhuyong
 */
public interface DemoRepository extends BaseRepository<User,String> {

    User findUserByName(String name);
}
