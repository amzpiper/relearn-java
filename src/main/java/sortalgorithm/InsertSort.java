package sortalgorithm;

import java.util.Arrays;

/**
 * 插入排序:
 * 也被称为直接插入排序。对于少量元素的排序,它是一个有效的算法
 * 
 * 最好情况时间复杂度:O(n)
 * 最坏情况时间复杂度:O(n²) 
 * 空间复杂度 O(1)
 * 两个相同的数的相对顺序不会发生改变,则该算法是稳定的
 * 
 * 工作原理:
 * 通过构建有序序列,对于未排序数据,在已排序序列中从后向前扫描,找到相应位置并插入,相应位置后的往后移动。
 * 插入排序和冒泡排序一样,也有一种优化算法,叫做拆半插入。
 * 
 * 算法步骤:
 * 将第一待排序序列第一个元素看做一个有序序列,把第二个元素到最后一个元素当成是未排序序列。
 * 从头到尾依次扫描未排序序列,将扫描到的每个元素插入有序序列的适当位置。
 * （如果待插入的元素与有序序列中的某个元素相等,则将待插入元素插入到相等元素的后面。）
 */
public class InsertSort {
    
    public static void main(String[] args) {
        int[] array = {78,69,55,43,55,31,20,10};
        sortInsert(array);
    }

    public static void sortInsert(int[] array){
        int[] arr = Arrays.copyOf(array, array.length);

        //从下标为1的元素开始选择合适的位置插入,因为下表为0的只有1个元素,默认是有序的
        for (int i = 1; i < arr.length; i++) {

            //保存要插入的值
            int temp = arr[i];
            System.out.println("第"+(i)+"轮：" + "交换" + temp + "");

            //从已经排序的最右侧开始比较并向后移动,找到比要插入的值小的数的下标,把要插入的值插入到这里,大的依次向后移动1格
            //i为排序和未排序的分界线
            int j = i;
            while(j > 0 && temp < arr[j-1]){
                System.out.println(j + "<==>" + (j-1));
                
                //大的向后移动
                arr[j] = arr[j-1];
                //继续向前遍历比较
                j--;
                
                for (int item : arr) {
                    System.out.print(item + " ");
                }
                System.out.println();
            }

            //why j != i
            //插入小的值
            if(j != i){
                arr[j] = temp;
                
                for (int item : arr) {
                    System.out.print(item + " ");
                }
                System.out.println();
            }

        }
    }

}
