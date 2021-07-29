package sortalgorithm;

import java.util.Arrays;

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
 * 希尔排序的基本步骤，在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，
 * 这种增量选择我们可以用一个序列来表示，{n/2,(n/2)/2...1}，称为增量序列。
 * 希尔排序的增量序列的选择与证明是个数学难题，我们选择的这个增量序列是比较常用的，也是希尔建议的增量，称为希尔增量，
 * 但其实这个增量序列不是最优的。此处我们做示例使用希尔增量。
 * 原始数据为一组[8,9,1,7,2 ,3,5,4,6,0],
 * 初始增量 gap = length/2 = 5,意味着整个数组被分为5组，[8,3] [9,5] [1,4] [7,6] [2,0],
 * 对这5组分别进行直接插入排序，结果如下，可以看到像3，1，0这些小元素被调到前面了,
 * [3,5,1,6,0,8,9,4,7,2]。
 * 然后缩小增量 gap = gap/2 = 2,数组被分为2组，[3,1,0,9,7] [5,6,8,4,2],
 * 对以上2组进行直接插入排序,结果为[0,2,1,4,3,5,7,6,9,8],可以看到整个数组更进一步了。
 * 再缩小增量 gap = gap/2 = 1,此时整个数组为1组[0,2,1,4,3,5,7,6,9,8],
 * 此时，仅仅需要对以上数列再进行1次直接插入排序后即可完成整个数组排序, 
 * 结果为[0,1,2,3,4,5,6,7,8,9]
 */
public class XiErSort {
    public static void main(String[] args) {
        int[] array = {8,9,1,7,2,3,5,4,6,0};
        sortXiEr(array);
    }

    /**
     * 希尔排序 针对有序序列在插入时采用移动法(类似插入排序的移动)。
     * 升序
     * @param array
     */
    public static void sortXiEr(int[] array){
        int[] arr = Arrays.copyOf(array,array.length);

        //增量gap,并逐步缩小增量,直到gap = 1
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            System.out.println("gap：" + "" + gap);
            for (int i = gap; i < arr.length; i++) {
                System.out.println("--------------------------");
                for (int item : arr) {System.out.print(item + " ");}System.out.println();
                
                //保存该组里第一个位置的值作为插入值
                int temp = arr[i];
                int j = i-gap;
                //如果后一个小,大的向后移动
                while(j >= 0 && arr[j]>temp){
                    System.out.println("move:"+(j+gap) + "=" + j);

                    //大的向后移动
                    arr[j+gap] = arr[j];
                    //j值保存前一个的位置的下标,只有在条件成立时才可以
                    j = j - gap;

                    for (int item : arr) {System.out.print(item + " ");}System.out.println();
                }
                //把最小值(插入值)插入到前面
                System.out.println("insert:"+(j+gap) + "=" + i);
                arr[j+gap] = temp;

                for (int item : arr) {System.out.print(item + " ");}System.out.println();
            }
            
            for (int item : arr) {System.out.print(item + " ");}System.out.println();
            System.out.println();
        }

    }

    /**
     * 降序
     * @param array
     */
    public static void sortXiErCopy(int[] array){
        int[] arr = Arrays.copyOf(array,array.length);  

        for (int gap = arr.length/2; gap > 0; gap/=2) {
            
            for(int i = gap;i<arr.length;i++){

                int j = i;
                int temp = arr[j];

                if(arr[j]>arr[j-gap]){
                    arr[j] = arr[j-gap];
                    j = j - gap;
                }
                arr[j] = temp;
            }
        }

        for (int item : arr) {System.out.print(item + " ");}System.out.println();
    }


}
