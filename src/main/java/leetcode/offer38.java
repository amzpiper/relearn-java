package leetcode;

import java.util.TreeSet;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * 
 * 限制：
 * 1 <= s 的长度 <= 8
 */
public class offer38 {
    public static void main(String[] args) {
        String[] s = permutation("abc");
        System.out.println(s);
    }

    public static String[] permutation(String s) {
        //1.拆分成字符
        char[] chars = s.toCharArray();

        //2.随机组合
        TreeSet<String> set = new TreeSet<String>();
        
        int count = 0;
        int maxLength = chars.length;
        int maxDeep = chars.length;
        int length = 0;
        int deep = 0;
        int index = 0;
        String root = null;
        
        /* 
        算法思路：
        把所有的字符都放到一个列中。
        判断是否达到最深：
        如果达到最深，处理完成；
        如果没有达到最深，把所有列中的数据都再往下一深度穷举一下，因为扩大的数据量，需要放到一个新的列中；
        再把新穷举存储下来的列用于下一次的判断。
        
        根据算法思路推算：
        需要穷举次数存量count
        需要深度变量，用于判断深度。deep
        需要两个列。一个保存穷举，一个用到存储新穷举。
        */

        //第一次穷举
        for(char c:chars){
            set.add(c+"");
        }
        count++;
        //判断是否还穷举
        while(count >= maxDeep){

        }

        //3.放到String[]
        String[] str = {""};
        return str;
    }


}
