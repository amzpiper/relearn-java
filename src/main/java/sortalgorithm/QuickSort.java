package sortalgorithm;

import java.util.Arrays;

/**
 * @auth guoyuhang
 * @data 2021/08/13
 * 快速排序:
 * 快速排序又是一种分而治之思想在排序算法上的典型应用。本质上来看,快速排序应该算是在冒泡排序基础上的递归分治法。
 * 事实上,快速排序通常明显比其他 Ο(nlogn) 算法更快,因为它的内部循环（inner loop）可以在大部分的架构上很有效率地被实现出来
 * 在最坏状况下则需要 Ο(n2) 次比较,但这种状况并不常见。
 * 它是处理大数据最快的排序算法之一了.
 * 快速排序的最坏运行情况是 O(n²),比如说顺序数列的快排。但它的平摊期望时间是 O(nlogn),
 * 且 O(nlogn) 记号中隐含的常数因子很小,比复杂度稳定等于 O(nlogn) 的归并排序要小很多。
 * 所以,对绝大多数顺序性较弱的随机数列而言,快速排序总是优于归并排序。
 * 
 * 算法步骤:
 * 1.从数列中挑出一个元素,称为 "基准"（pivot）;
 * 2.重新排序数列,所有元素比基准值小的摆放在基准前面,所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后,该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
 * 
 */
public class QuickSort {
    
    public static void main(String[] args) {
        int[] array = {78,69,31,43,55,10,20,88,99};

        int[] arr = Arrays.copyOf(array, array.length);
        quickSort(arr,0,arr.length - 1);
    }

    public static int[] quickSort(int[] arr , int left, int right){


        return arr;
    }

    public static int partition(int[] arr , int left, int right){
        int pivot = left;

        return pivot;
    }

    public static void swap(int[] arr,int i,int j){
        
    }

}
