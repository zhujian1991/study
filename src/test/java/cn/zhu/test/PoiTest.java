package cn.zhu.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.zhu.test.entity.StudentEntity;
import cn.zhu.test.jpa.IStudentRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

// 运行器，代表在什么环境下运行，@RunWith(JUnit4.class)☞JUnit4来运行
@RunWith(SpringRunner.class)
// springBoot专用于单元测试的注解。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PoiTest {
    @Autowired
    private TestRestTemplate template;
    /*  从配置读取数据
        @Autowired 顾名思义，就是自动装配。其作用是替代Java代码里面的getter/setter与bean属性中的property。
        @Autowired 的原理是什么？
        　　其实在启动spring IoC时，容器自动装载了一个AutowiredAnnotationBeanPostProcessor后置处理器，
        当容器扫描到@Autowied、@Resource或@Inject时，就会在IoC容器自动查找需要的bean，并装配给该对象的属性
     */
    @Autowired
    private IStudentRepository studentRepository;
    @org.junit.Test
    public void getHello() throws Exception{
        try {
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
            //构造对象等同于@Excel
            ExcelExportEntity excelentity = new ExcelExportEntity("姓名", "name");
            excelentity.setNeedMerge(true);
            entity.add(excelentity);
            entity.add(new ExcelExportEntity("体重", "hight"));
            List<StudentEntity> all = studentRepository.findAll();
            //把我们构造好的bean对象放到params就可以了
            long start = System.currentTimeMillis();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("测试", "测试"), entity,
                    all);
            FileOutputStream fos = new FileOutputStream("D:/ss.xls");
            workbook.write(fos);
            fos.close();
            long end = System.currentTimeMillis();

            System.out.println("耗时："+ (end -start));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @org.junit.Test
    public void sayYes() throws Exception{



    }

}
