package sortalgorithm;

/**
 * 插入排序：
 * 
 * 最好情况时间复杂度：O(n)
 * 最坏情况时间复杂度：O(n²) 
 * 空间复杂度 O(1)
 * 稳定
 * 
 * 工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序和冒泡排序一样，也有一种优化算法，叫做拆半插入。
 * 
 * 算法步骤：
 * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
 * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。
 * （如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
 */
public class InsertSort {
    
    public static void main(String[] args) {
        int[] array = {78,69,55,43,31,20,10};
        sortInsert(array);
    }
    public static void sortInsert(int[] array){
        
    }
}
