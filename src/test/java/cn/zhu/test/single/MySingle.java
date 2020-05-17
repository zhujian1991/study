package cn.zhu.test.single;

public class MySingle {
    private MySingle(){}
    private static MySingle instant = null;
    public static MySingle getInstant() {
        if(instant==null){
            instant = new MySingle();
        }
        return instant;
    }
    public void getString(){
        System.out.println("单例里面");
    }
}
