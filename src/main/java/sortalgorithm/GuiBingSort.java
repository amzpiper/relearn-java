package sortalgorithm;

import java.util.Arrays;

/**
 * 2路-归并排序:
 * 归并排序的实现由两种方法:
 * 自上而下的递归(所有递归的方法都可以用迭代重写,所以就有了第 2 种方法)；
 * 自下而上的迭代；
 * 
 * 自上而下的递归:
 * 对一个数组(str)选中一个中间位置(mid=(start+end)/2),分别进行左递归(mergeSort(str,start,mid,length)),右递归(mergeSort(str,mid+1,end,length)),在回朔的时候分别对以中间为分割的数组进行排序(merge(str,start,end,mid)),此时是一个归并的过程,这是自上而下的方法。
 * 自下而上的递归:
 * 自下而上归并其实就是自上而下的时候的回朔过程,先对每一个数字排序,在两两排序,在对结果两两排序,直到完成。
 * 
 * 和选择排序一样,归并排序的性能不受输入数据的影响,
 * 但表现比选择排序好的多,因为始终都是 O(nlogn) 的时间复杂度。代价是需要额外的内存空间。
 * 最好情况时间复杂度:O(n log n)
 * 最坏情况时间复杂度:O(n log n)
 * 平均时间复杂度:O(n log n)
 * 空间复杂度(如果不是从下到上): O(n) + O( log n)
 * 空间复杂度(从下到上): O(1)
 * 稳定排序算法。
 * 
 * 算法步骤:
 * 1.申请空间,使其大小为两个已经排序序列之和,该空间用来存放合并后的序列；
 * 2.设定两个指针,最初位置分别为两个已经排序序列的起始位置；
 * 3.比较两个指针所指向的元素,选择相对小的元素放入到合并空间,并移动指针到下一位置；
 * 4.重复步骤 3 直到某一指针达到序列尾；
 * 5.将另一序列剩下的所有元素直接复制到合并序列尾。
 * 
 * 例子:49,38,65,97,76,13,27
 * 1.首先将整个序列的每一个关键字看作是一个单独的有序的子序列
 * 2.对所有子序列进行两两归并,49和38归并长度为2的有序序列{38,49},65和97归并为长度为2的有序序列{65,97},76和13归并长度为2的有序序列{13,76},27没有归并对象
 * 3.对所有子序列再进行两两归并,{38,49}和{65,97}归并为长度为4的有序序列{38,49,65,97},{13,76}和{27}归并为长度为3的有序序列{13,27,76}
 * 4.对所有子序列再进行两两归并,{38,49,65,97}和{13,27,76}归并为长度为7的有序序列{13,27,38,49,65,76,97}
 * 
 */
public class GuiBingSort {
    public static void main(String[] args) {
        int[] array = {49,38,65,97,76,13,27};
        int[] arr;

        //自上而下递归
        arr = sort(array);
        System.out.println("arr.length < 2,结果:");
        for (int item : arr) {System.out.print(item + " ");}System.out.println();
        System.out.println();

        //自下而上递归
        arr = sort2(array);
        System.out.println("arr.length < 2,结果:");
        for (int item : arr) {System.out.print(item + " ");}System.out.println();
        System.out.println();

    }

    /**
     * 采用自上而下的递归方法
     * @param array
     */
    public static int[] sort(int[] array){
        int[] arr = Arrays.copyOf(array, array.length);
        
        if (arr.length < 2) {
            return arr;
        }

        int middle = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        arr = merge(sort(left),sort(right));
    }

    protected static int[] merge(int[] left,int[] right){
        int[] result = new int[left.length + right.length];

        return result;
    }



    /**
     * 自下而上的迭代
     * @param array
     */
    public static int[] sort2(int[] array){
        return null;
    }

    protected static int[] merge2(int[] left,int[] right){
        int[] result = new int[left.length + right.length];

        return result;
    }
}
