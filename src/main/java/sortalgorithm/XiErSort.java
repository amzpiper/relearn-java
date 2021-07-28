package sortalgorithm;

import org.bouncycastle.util.Arrays;

/**
 * 希尔排序：
 * 
 * 于1959年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，
 * 也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一。
 * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率；
 * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位；
 * 
 * 基本思想是：
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
 * 待整个序列中的记录"基本有序"时，再对全体记录进行依次直接插入排序。
 * 
 * 最好情况时间复杂度：O(n log² n)
 * 最坏情况时间复杂度：O(n log² n)
 * 平均时间复杂度：O(n log n)
 * 空间复杂度 O(1)
 * 希尔排序是非稳定排序算法。
 *  
 * 算法步骤:
 * 
 * 
 */
public class XiErSort {
    public static void main(String[] args) {
        int[] array = {78,69,55,43,56,31,20,10,54,32,26,12};
        sortXiEr(array);
    }

    public static void sortXiEr(int[] array){
        int[] arr = Arrays.clone(array);

    }

}
