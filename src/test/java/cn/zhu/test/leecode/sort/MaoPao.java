package cn.zhu.test.leecode.sort;

/**
 * description 冒泡排序
 * @className MaoPao
 * @author 程序老仁
 * @date 2020/9/6
 */   

public class MaoPao {
    public static void main(String[] args) {
        int[] s ={2,7,4,2,8,3,21,123,534,1223,12,33,1};
        int[] ints = get(s);
        for (int i:ints
             ) {
            System.out.println(i);
        }
        System.out.println(get(s).toString());
    }

    public static int[] get(int[] old){
        int tem =0;
        for(int i =0 ; i<old.length-1 ; i++){
            for(int j=0; j<old.length-1 -i ; j++){
                if(old[j]>old[j+1]){
                    tem = old[j];
                    old[j]=old[j+1];
                    old[j+1]=tem;
                }
            }
        }
       return old;
    }
}
