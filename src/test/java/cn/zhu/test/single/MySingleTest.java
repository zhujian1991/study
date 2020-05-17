package cn.zhu.test.single;
import org.junit.Test;

public class MySingleTest {
    @Test
    public void test(){
        String ssssss = "2020-02-31";
        System.out.println(ssssss.substring(0,4));
        System.out.println(ssssss.substring(5,7));
        System.out.println(ssssss.substring(8,10));
        System.out.println("222222222222222229-------------------------");
        MySingle mySingle = MySingle.getInstant();
        mySingle.getString();
        MySingle instant = MySingle.getInstant();
        System.out.println(mySingle);
        System.out.println(instant);

        EnumTest in = EnumTest.IN;
        EnumTest out = EnumTest.OUT;

        System.out.println(in);
        System.out.println(out);

        String s = EnumTest.IN.get("ss");
        String s1 = EnumTest.OUT.get("ss");
        System.out.println(s);
        System.out.println(s1);
        System.out.println(in==out);

    }

}
