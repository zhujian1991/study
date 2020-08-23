package cn.zhu.test.enumDemo;

public enum SelectFoodsEnum {
    INSTANT;

    public static SelectFoodsEnum getInstant(){
        return INSTANT;
    }

    public String getName(){
        return "这是单例的name";
    }

    private static FoodsCtrategy instant= FoodsCtrategy.EXPRESS;

    public static FoodsCtrategy getInstance(){
        return instant;
    }

}
