package cn.zhu.test.lambda;

import cn.zhu.test.entity.UserEntity;
import cn.zhu.test.service.lambda.LambdaService;
import cn.zhu.test.service.lambda.IGetPerson;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类
 * @author 程序老仁
 * @date 2020/5/5
 */   

public class LambdaTest {


    @org.junit.Test
    public void getHello() throws Exception {
//        testArrayList();
//        testMap();
//        testStream();

    }


    /**
     * description stream测试
     * @author 程序老仁
     * @date 2020/5/11
     */
    private void testStream() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too","yesOrNo", "too"));
        System.out.println(list.toString());
        list.forEach(str -> {
            System.out.println(str);
        });
        Stream stream = Stream.of(list);
        stream.distinct().forEach(s ->{
            System.out.println(s);
        });
        Stream<String> stream1= Stream.of("I", "love", "you", "too", "too");
        stream1.distinct()
                .forEach(str -> System.out.println(str));
        //
        try{
            List<String> result = stream1.collect(Collectors.toList());
            result.forEach(s -> System.out.println(s));
        }catch (Exception e){
            System.out.println("stream1" + "转换错误,流被操作过了，就会被回收");
        }
        System.out.println("------");
        Stream<String> stream2= Stream.of("I", "love", "you", "too", "too");
        List<String> result = stream2.collect(Collectors.toList());
        result.forEach(s -> System.out.println(s));
        System.out.println("--xxxxxxxxxx----");
        ArrayList<String> streamList = new ArrayList<>(Arrays.asList("I", "love", "you", "too","yesOrNo", "too"));
        streamList.stream().forEach(s -> { if(s.length()>5){
            System.out.println(s);
        } });
        System.out.println("-------4 号-------------------------------");
        List<String> collect = streamList.stream().filter(s -> s.length() > 5).collect(Collectors.toList());
        System.out.println(collect.toString());
        System.out.println("-------------------5号-----");
        collect.forEach(s -> System.out.println(s));

    }

    /**
     * description map 的测试
     * @author 程序老仁
     * @date 2020/5/10
     */
    private void testMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        System.out.println("------------- 1号分割线————————");
        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                System.out.println(integer + "=" + s);
            }
        });
        System.out.println("------------- 2号分割线————————lambda map的forech");
        map.forEach((x,y)-> System.out.println(x + " = " + y));
        System.out.println("------------- 3号分割线————————lambda map的forech");
        map.forEach((x,y)-> {
            if(StringUtils.isNotBlank(y) && y.equalsIgnoreCase("two")){
                System.out.println("找到了数据 key："+ x +" -- value: " + y );
            }
        });
        System.out.println("------------- 4号分割线————————lambda getOrDefault");
        String xxx = map.getOrDefault("1", "xxx");
        System.out.println(xxx);
        String xxxa = map.getOrDefault(1, "xxx");
        System.out.println(xxxa);
        System.out.println("------------- 5号分割线————————map remove");
        System.out.println(map.toString());
        map.remove(1,"11");
        System.out.println(map.toString());
        map.remove(1);
        System.out.println(map.toString());
        map.remove(3,"three");
        System.out.println(map.toString());
        System.out.println("------------- 5号分割线————————lambda getOrDefault");

    }

    /**
     * description 集合测试
     * @author 程序老仁
     * @date 2020/5/10
     */
    private void testArrayList() {
        // 使用forEach()结合匿名内部类迭代
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too","yesOrNo"));
        // 对list元素进行处理
        list.forEach(new Consumer<String>(){
            @Override
            public void accept(String str){
                if(str.length()>3)
                    System.out.println(str);
            }
        });
        System.out.println(list.toString());
        list.add("11331");
        list.forEach(s -> {
            if(s.length()>3){
                System.out.println(s);
            }
        });
        System.out.println(list.toString());
        System.out.println("---------------- 分割线 1 ------------");
        list.removeIf(s -> s.length()>5);
        System.out.println(list.toString());
        System.out.println("---------------- 分割线 2 ------------替换 ");
        list.replaceAll(s -> s.replace("11","x1"));
        System.out.println(list.toString());
        System.out.println("---------------- 分割线 3 ------------本身排序按照首字母");
        // 本身的字符串排序是按照首字母 排序
        Collections.sort(list);
        System.out.println(list.toString());
        System.out.println("---------------- 分割线 4 ------------大小写替换");
        list.replaceAll(s -> s.toUpperCase());
        System.out.println(list.toString());
        System.out.println("---------------- 分割线 5 ------------原始长度排序重写");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length()-o1.length();
            }
        });
        System.out.println(list.toString());
        System.out.println("---------------- 分割线 6 ------------ lambda");
        Collections.sort(list,(x,y) ->{ return  x.length()-y.length() ;});
        System.out.println(list.toString());



    }

    /**
     * description 基本测试
     * @author 程序老仁
     * @date 2020/5/10
     */
    private  void get(){
        LambdaService re = ((x, y) -> x * y);
        Integer integer = re.get(5, 6);
        System.out.println(integer);
        System.out.println("--------------------------------");
        UserEntity UserEntity2 = new UserEntity();
        UserEntity2.setId(22);
        UserEntity2.setName("王子");
        UserEntity UserEntity1 = new UserEntity();
        UserEntity1.setId(23);
        UserEntity UserEntity3 = new UserEntity();
        UserEntity3.setId(24);
        UserEntity3.setName("皇帝宠妃");

        IGetPerson iGetPerson = new IGetPerson() {
            @Override
            public Boolean getLenth(UserEntity UserEntity) {
                if (UserEntity.getId() > 23) {
                    return true;
                }
                return false;
            }
        };
        System.out.println(iGetPerson.getLenth(UserEntity1));
        System.out.println(iGetPerson.getLenth(UserEntity2));
        System.out.println(iGetPerson.getLenth(UserEntity3));

        System.out.println("-------------- 2号分割 -------------");
        IGetPerson iGetPersonLambda = (UserEntity -> UserEntity.getId()>23);
        System.out.println(iGetPersonLambda.getLenth(UserEntity1));
        System.out.println(iGetPersonLambda.getLenth(UserEntity2));
        System.out.println(iGetPersonLambda.getLenth(UserEntity3));
        System.out.println("-------------- 3号分割 -------------");

        IGetPerson iGetPersonLambda2 = (UserEntity -> (StringUtils.isBlank(UserEntity.getName()) ? 0 : UserEntity.getName().length())>3);
        System.out.println(iGetPersonLambda2.getLenth(UserEntity1));
        System.out.println(iGetPersonLambda2.getLenth(UserEntity2));
        System.out.println(iGetPersonLambda2.getLenth(UserEntity3));
        System.out.println("-------------- 4号分割 -------------");
        // 调用特定方法的get方法，就是调用静态方法
        Supplier<String> s2 = Fun :: getString;
        System.out.println(s2.get());
        Supplier<String> s3 = StringLocalUtils ::substring;
        System.out.println(s3.get());
        Supplier<String> s4 = () -> StringLocalUtils.substring("祝二狗");
        System.out.println(s4.get());
        System.out.println("-------------- 5号分割 -------------");
        Consumer<String> c1 = StringLocalUtils :: substring;
        c1.accept("皇帝");
        System.out.println("-------------- 6号分割 -------------");
        Function<String,String> f1 = StringLocalUtils :: substring;
        Function<String,String> f2 = (test)-> StringLocalUtils.substring();
        String f21 = f2.apply("祝大");
        System.out.println(f21);
        String f11 = f1.apply("主打1");
        System.out.println(f11);
        System.out.println("-------------- 7号分割 -------------");
        BiFunction<String,String,String> b1 = StringLocalUtils :: substring;
        BiFunction<String,String,String> b2 =(x,y) -> StringLocalUtils.substring();
        BiFunction<String,String,String> b4 =(x,y) -> StringLocalUtils.substring(x);
        BiFunction<String,String,String> b3 =(x,y) -> StringLocalUtils.substring(x,y);
        String xx = b1.apply("xx", "22");
        System.out.println(xx);
        String gg = b2.apply("gg", "33");
        System.out.println(gg);
        String x4 = b4.apply("ss3", "rr3");
        System.out.println(x4);
        String xe = b3.apply("ss", "rr");
        System.out.println(xe);
        System.out.println("-------------- 8号分割 -------------");
        StringLocalUtils utils = new StringLocalUtils();
        Supplier<String> u1 = () -> utils.getString("祝二狗");
        Consumer<String> u2 = utils :: getString;
        System.out.println(u1.get());
        u2.accept("ggg");
        System.out.println("-------------- 9号分割 -------------");
        Consumer<StringLocalUtils> con1 = StringLocalUtils::getString;
        Consumer<StringLocalUtils> con2 =(StringLocalUtils util) -> StringLocalUtils.getStringa();
        Consumer<StringLocalUtils> con3 =(too) -> new StringLocalUtils().getString();
        con1.accept(utils);
        con2.accept(utils);
        con3.accept(utils);

    }

}

class  Fun {
    public  static  String getString(){
        return "ggg";
    }
}