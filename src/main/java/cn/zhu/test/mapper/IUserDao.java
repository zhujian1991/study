package cn.zhu.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import cn.zhu.test.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 
 * </p>
 * 
 * @version 1.0
 * @author 程序老仁
 * @since 2020-05-31
 */
@Mapper
public interface IUserDao extends BaseMapper<UserEntity>{

	Integer insertBatch(List<UserEntity> list);
}
