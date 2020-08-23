package cn.zhu.test.service.excel.impl;

import cn.afterturn.easypoi.csv.entity.CsvExportParams;
import cn.afterturn.easypoi.csv.export.CsvExportService;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.zhu.test.config.db.ReadOnly;
import cn.zhu.test.dto.ReqStudentDto;
import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.jpa.IStudentRepository;
import cn.zhu.test.mapper.IStudentDao;
import cn.zhu.test.service.excel.IStudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IStudentDao studentDao;

    @Override
    public void excelStudents(ReqStudentDto dto) throws IOException {
        // 设置导出字段对应的名字
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("编号", "id"));
        entity.add(new ExcelExportEntity("名字", "name"));
        entity.add(new ExcelExportEntity("身高", "hight"));

        String title = "学生信息导出";
        String sheet = "学生信息导出";

        // 导出异步方法
        File file = File.createTempFile("学生信息导出-" + System.nanoTime() + "", ".csv", new File(System.getProperty("java.io.tmpdir")));
        // 设置导出字段对应的名字
        FileOutputStream outputStream = new FileOutputStream(file);
        // 组装查询语句
        QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
        concatStudentParams(dto, wrapper);
        Page<StudentEntity> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        long total = studentDao.selectCount(wrapper);
        CsvExportParams params = new CsvExportParams();
        params.setEncoding("gbk");
        params.setTextMark("\t");
        // 设置导出信息
        CsvExportService cs = new CsvExportService(outputStream, params, entity);
        List<StudentEntity> entities = new ArrayList<>(2000);
//        IPage<StudentEntity> list = null;
        try {
                // 如果大于设定阀值，则进行分批查询
                Long index = (long) total /2000;
                // 判断最后是否还有值，有值则+1
                index += 1;
                page.setSize(2000);
                page.setPages(index);
                for (int i = 0; i < index; i++) {
                    page.setCurrent(i + 1);
//                    list = studentDao.selectPage(page, wrapper);
                    studentDao.selectPage(page,wrapper);
                    entities = page.getRecords();
                    cs.write(entities);
                    System.out.println("这是第 "+i+" 页， Ok");
                    entities.clear();
            }
        }catch (Exception e){
            cs.close();
        }
        // 上传
    }

    @Override
    public Response queryStudents(ReqStudentDto dto) {
        QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
        concatStudentParams(dto, wrapper);
        IPage<StudentEntity> page = new Page<>(dto.getPageNo(), dto.getPageSize());
        studentDao.selectPage(page, wrapper);
        Response response = new Response();
        Map<String, Object> vendorExtensions = new LinkedHashMap();
        vendorExtensions.put("date",page.getRecords());
        response.setVendorExtensions(vendorExtensions);
        return response;
    }

    @Override
    @ReadOnly
    public List<StudentEntity> all() {
        List<StudentEntity> all = studentDao.getAll();
        return all;
    }

    @Override
    public List<StudentEntity> allW() {
        List<StudentEntity> all = studentDao.getAllW();
        return all;
    }

    /**
     * description 
     * @param dto 查询对象实体，wrapper mybatispuls需要查询的查询条件对象
     * @author 程序老仁
     * @date 2020/5/31
     */
    private void concatStudentParams(ReqStudentDto dto, QueryWrapper<StudentEntity> wrapper) {
        Integer id = dto.getId();
        if(null != id){
            wrapper.eq("id",dto.getId());
        }
        if(StringUtils.isNotBlank(dto.getName())){
            wrapper.eq("name",dto.getName());
        }
    }
}
