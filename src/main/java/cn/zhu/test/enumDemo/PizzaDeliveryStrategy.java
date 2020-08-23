package cn.zhu.test.enumDemo;

import cn.zhu.test.entity.Pizza;

public enum PizzaDeliveryStrategy {
    EXPRESS {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("披萨好吃");
        }
    },
    NORMAL {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("披萨难吃");
        }
    };

    public abstract void deliver(Pizza pz);
}
