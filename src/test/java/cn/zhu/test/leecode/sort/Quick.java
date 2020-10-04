package cn.zhu.test.leecode.sort;

/**
 * description 快排
 * @className Quick
 * @author 程序老仁
 * @date 2020/9/6
 */

public class Quick {
    public static void main(String[] args) {

        int size = 7;
        int[] nums = {49,38,65,97,76,13,27};
        System.out.println("原始数据：");
        System.out.println(arrayToStr(nums,size));


        System.out.println("排序开始");
        quickSort(nums);//排序的主函数
        System.out.println("排序结束");
        System.out.println("排序后数据：");
        System.out.println(arrayToStr(nums,size));
    }

    public static int [] get (int [] ints){

        return ints;
    }
    /**
     * 将数组打印出来的函数，辅助函数，无需过多关注
     * @param nums
     * @param size
     * @return
     */
    private static String arrayToStr(int[] nums,int size){
        String str = "";
        for(int i=0;i<size;i++){
            str += nums[i]+" ";
        }
        return str;
    }


    /**
     * 快速排序的主接口
     * @param nums
     * @param size
     */
    public static void quickSort(int[] nums){
        quickSort(nums,0,nums.length-1);
    }

    /**
     * 快速排序的主接口，用于实现排序
     * @param nums
     * @param start
     * @param end
     */
    private static void quickSort(int[] nums,int start,int end){
        //函数的返回条件，当start=end时，排序结束
        if(end<=start){
            return;
        }
        else{
            //一遍排序,重点理解，midle表示已排好序的数字位置
            int midle = onceQuickSort(nums,start,end);
            quickSort(nums,start,midle-1);//对midle左边的数据，进行排序
            quickSort(nums,midle+1,end);//对midle右边的数据，进行排序
        }

    }


    /**
     * 快速排序的重点，在一遍排序
     * 功能：数组nums[start-end]，将小于nums[start]的树放在左边，大于nums[start]的树放在右边
     * 返回值是nums[start]的最终位置
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int onceQuickSort(int[] nums,int start,int end){
        int povit = nums[start];//这次排序的参照数
        int left = start,right = end;
        //right从右到左，left从左到右移动，当left==right时，这遍排序结束
        while(left<right){
            //right从右到左开始遍历
            while(left<right && nums[right]>povit){
                right--;
            }
            //left从左到右开始遍历
            while(left<right && nums[left]<povit){
                left++;
            }
            //当遇到left和right都不能移动时，nums[right]和nums[left]交换位置
            if(left<right){
                swap(nums,left,right);
            }
        }
        //此时left==right
        nums[left] = povit;
        return left;


    }


    /**
     * 交换nums[i]和nums[j]的位置
     * @param nums
     * @param i
     * @param j
     */
    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
