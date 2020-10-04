package cn.zhu.test.leecode.sort;

import org.apache.poi.util.ArrayUtil;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;

/**
 * description 插入排序
 * @className ChaRu
 * @author 程序老仁
 * @date 2020/9/6
 */

public class ChaRu {
    public static void main(String[] args) {
        int[] s = {2, 7, 1};
        int[] ints = get(s);
        for (int i:ints
             ) {
            System.out.println(i);
        }

    }

    public static int[] get(int[] old) {
        int k =0;
        int min = 0;
        for(int i = 0;i<old.length-1;i++){
            min = old[i];
            k = 0;
            for(int j = i+1;j<old.length;j++){
                if (min > old[j]) {
                    min = old[j];
                    k=j;
                }
            }
            if(k!=0){
                int  a = old[k];
                old[k]=old[i];
                old[i]= a;
            }
        }

        return old;
    }
}
