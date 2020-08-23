package cn.zhu.test.enumDemo;


import cn.zhu.test.entity.Foods;

public enum  FoodsCtrategy {
    EXPRESS {
        @Override
        public void getFoods(Foods foods) {
            System.out.println("披萨好吃");
        }
    },
    NORMAL {
        @Override
        public void getFoods(Foods foods) {
            System.out.println("披萨难吃");
        }
    };
    public abstract void getFoods(Foods foods);
}
