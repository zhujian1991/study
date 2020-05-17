package cn.zhu.test.lambda;

/**
 * @className StringLocalUtils
 * @author 程序老仁
 * @date 2020/5/7
 */
public class StringLocalUtils {

    public static String substring(){
        System.out.println("测试1打印");
        return "这是第一个测试";
    };

    public static String substring(String xx){
        System.out.println("测试打印2");
        return "这是第一个测试名称2:"+xx;
    };

    public static String substring(String xx,String yy){
        System.out.println("测试打印3");
        return "这是第一个测试名称3:"+xx+" 和："+yy;
    };

    public String getString(String xx){
        System.out.println("getString 打印");
        return " 打印getString ";
    };
    public String getString(){
        System.out.println("getString 打印 空的");
        return " 打印getString 空的 ";
    };

    public static String getStringa(){
        System.out.println("getString 打印 空的aaaaa");
        return " 打印getString 空的aaa ";
    };
}
