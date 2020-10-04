package cn.zhu.test.leecode;

/**
 * description 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @className StringNum
 * @author 程序老仁
 * @date 2020/8/23
 */   

public class StringNum {

    public static void main(String[] args) {
        int ggnwwm = lengthOfLongestSubstring("5698ewqqlckpdddds");
        System.out.println("最后的输出:"+ggnwwm);
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        String target = "";
        String demo = "";
        int num = 0;
        for (int i = 0; i <chars.length ; i++) {
            demo = String.valueOf(chars[i]);
            for (int j = i+1; j < chars.length; j++) {
                if (demo.contains(String.valueOf(chars[j]))) {
                    if(demo.length()>num){
                        target = demo;
                        num = demo.length();
                    }
                    break;
                }
                demo=demo+chars[j];
            }
        }
        num = target.length();
        System.out.println("字符："+target);
        return num;
    }
    /**
     * description 
     * @param 错误
     * @author 程序老仁
     * @date 2020/8/23
     * @return 
     */   
    public static int lengthOfLongestSubstring111(String s) {
        char[] chars = s.toCharArray();
        String target = "";
        int num = 0;
        for (int i = 0; i <chars.length ; i++) {
            if(i==0){ target=target+chars[0];   continue;}
            if(target.contains(String.valueOf(chars[i]))){
                target=target.substring(1,target.length());
            }
            target=target+chars[i];
        }
        num = target.length();
        return num;
    }
}
