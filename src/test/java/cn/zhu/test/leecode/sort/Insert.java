package cn.zhu.test.leecode.sort;

/**
 * description 插入排序
 * @className Insert
 * @author 程序老仁
 * @date 2020/9/6
 */   

public class Insert {
    public static void main(String[] args) {
        int[] s ={2,7,4,2,8,3,21,123,534,1223,12,33,1};

        int[] ints = get(s);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }

    public static int[] get (int[] ints){
        for(int i =1;i<ints.length;i++){
            for(int j=i;j>0;j--){
                if(ints[j-1]<ints[j]){
                    break;
                }
                int a = ints[j-1];
                ints[j-1]=ints[j];
                ints[j]=a;
            }
        }
        return ints;
    }

}
