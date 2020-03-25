package cn.zhu.test;

import cn.zhu.test.bean.User;
import cn.zhu.test.dao.StudentDao;
import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.regex.Pattern;

// 运行器，代表在什么环境下运行，@RunWith(JUnit4.class)☞JUnit4来运行
// springBoot专用于单元测试的注解。
public class DemoTest {
    /*  从配置读取数据
        @Autowired 顾名思义，就是自动装配。其作用是替代Java代码里面的getter/setter与bean属性中的property。
        @Autowired 的原理是什么？
        　　其实在启动spring IoC时，容器自动装载了一个AutowiredAnnotationBeanPostProcessor后置处理器，
        当容器扫描到@Autowied、@Resource或@Inject时，就会在IoC容器自动查找需要的bean，并装配给该对象的属性
     */

    @org.junit.Test
    public void getHello() throws Exception{
        String aaa ="淘宝客退款[123123]";
        String test="34556";
        //FileOutputStream fos = new FileOutputStream("D:/sssss.xls");
        File file = new File("D:/sssss.xls");
        boolean read = file.canRead();

         FileInputStream fis = new FileInputStream("D:/aaa.txt");
         FileOutputStream fos = new FileOutputStream("D:/bbb.txt");
                 //如果没有bbb.txt,会创建出一个

         int b;
         while((b=fis.read())!=-1){
               fos.write(b);
           }
         fis.close();
         fos.close();

//        System.out.println(read);
//        boolean write = file.canWrite();
//        System.out.println(write);
//        boolean hidden = file.isHidden();
//        System.out.println(hidden);

//        System.out.println(file.getPath());
//        System.out.println(file.getAbsoluteFile());
//        System.out.println(file.getCanonicalFile());
//        System.out.println(file.getParent());
//        System.out.println(file.length());
//        System.out.println(file.getName());


//        System.out.println(aaa.substring(2));
//        char c = test.charAt(4);
//        int le = 0;
//        for (int i=le; i < test.length(); i++) {
//            System.out.println(i);
//            System.out.println("----"+test.charAt(i));
//        }


        //String order = getOrder(test, 5);



        //System.out.println("最终有："+order);



//        StudentEntity user = new StudentEntity();
//
//        for (int i = 0; i < 1000000 ; i++) {
//            //user.setId(Long.valueOf(i));
//            user.setName("狗子1"+i);
//            user.setHight(i);
//            dao.save(user);
//        }
//        System.out.println("程序老仁的getHello入口 +++++++++++++  ");
//        System.out.println("程序老仁配置的姓名："+userDemo.getName());
//        System.out.println("程序老仁配置的年龄："+userDemo.getAge());
//
//        Boolean aaa=  true;
//
//        while (aaa){
//            System.out.println("你进来了吗");
//            aaa = false;
//        }
    }
    private String getOrder(String in ,int length){
        if(StringUtils.isBlank(in) && in.length()<=length){
            return "";
        }
        int endString = in.length();
        System.out.println(endString);
        String substring = in.substring(endString - length, endString);
        System.out.println(substring);

        return isInteger(substring) ? substring : "" ;

    }





    private   boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    private String getTest(String in ,char start,String end){
        if(StringUtils.isBlank(in)){
            return "";
        }
        int endString = in.indexOf(end);
        System.out.println(endString);
        int startString = -1;
        for(int i=endString-1;i>=0;i--){
            System.out.println(i);
            char c = in.charAt(i);
            System.out.println(c);
            if(c == start){
                startString=i+1;
                break;
            }
        }
        if (startString==-1)return "";
        if (endString >startString) {
            return in.substring(startString,endString);
        }
        return "";

    }
}
