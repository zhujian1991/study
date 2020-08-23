package cn.zhu.test.service.lambda;

public class LambdaServiceImpl implements LambdaService {

    @Override
    public Integer get(int x, int y) {
        return x+y;
    }
}
