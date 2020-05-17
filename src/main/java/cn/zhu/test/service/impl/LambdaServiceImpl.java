package cn.zhu.test.service.impl;

import cn.zhu.test.service.LambdaService;

public class LambdaServiceImpl implements LambdaService {

    @Override
    public Integer get(int x, int y) {
        return x+y;
    }
}
