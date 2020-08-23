package cn.zhu.test.service.excel;

import cn.zhu.test.dto.ReqStudentDto;
import cn.zhu.test.entity.StudentEntity;
import io.swagger.models.Response;

import java.io.IOException;
import java.util.List;

public interface IStudentService {

    /**
     * description 导出
     * @param
     * @author 程序老仁
     * @date 2020/5/31
     * @return 
     */   
    void excelStudents(ReqStudentDto dto) throws IOException;

    /**
     * description 
     * @param dto 查询条件对象
     * @author 程序老仁
     * @date 2020/5/31
     * @return Response
     */
    Response queryStudents(ReqStudentDto dto);

    /**
     * description
     * @author 程序老仁
     * @date 2020/5/31
     * @return Response
     */
    List<StudentEntity> all();

    /**
     * description
     * @author 程序老仁
     * @date 2020/5/31
     * @return Response
     */
    List<StudentEntity> allW();

}
