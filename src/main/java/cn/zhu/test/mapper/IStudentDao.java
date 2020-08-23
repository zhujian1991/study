package cn.zhu.test.mapper;

import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
public interface IStudentDao extends BaseMapper<StudentEntity> {

	Integer insertBatch(List<StudentEntity> list);

	List<StudentEntity> getAll();

	List<StudentEntity> getAllW();

}
