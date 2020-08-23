package cn.zhu.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import cn.zhu.test.entity.AnimalEntity;
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
public interface IAnimalDao extends BaseMapper<AnimalEntity>{

	Integer insertBatch(List<AnimalEntity> list);
}
