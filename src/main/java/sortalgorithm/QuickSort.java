package sortalgorithm;

import java.util.Arrays;

/**
 * @auth guoyuhang
 * @data 2021/08/13
 * 快速排序:
 * 快速排序又是一种分而治之思想在排序算法上的典型应用.本质上来看,快速排序应该算是在冒泡排序基础上的递归分治法.
 * 事实上,快速排序通常明显比其他 Ο(nlogn) 算法更快,因为它的内部循环(inner loop)可以在大部分的架构上很有效率地被实现出来
 * 在最坏状况下则需要 Ο(n2) 次比较,但这种状况并不常见.
 * 它是处理大数据最快的排序算法之一了.
 * 快速排序的最坏运行情况是 O(n²),比如说顺序数列的快排.但它的平摊期望时间是 O(nlogn),
 * 且 O(nlogn) 记号中隐含的常数因子很小,比复杂度稳定等于 O(nlogn) 的归并排序要小很多.
 * 所以,对绝大多数顺序性较弱的随机数列而言,快速排序总是优于归并排序.
 * 
 * 快速排序不是一种稳定的排序算法
 * 
 * 算法步骤:
 * 1.从数列中挑出一个元素(通常选用数组的第一个数),称为 "基准"(pivot);
 * 2.重新排序数列,所有元素比基准值小的摆放在基准前面,所有元素比基准值大的摆在基准的后面(相同的数可以到任一边).
 * 在这个分区退出之后,该基准就处于数列的中间位置.这个称为分区(partition)操作；
 * 3.递归地(recursive)把小于基准值元素的子数列和大于基准值元素的子数列排序；
 * 
 * 一趟快速排序的算法是：
 * 设置两个变量i、j，排序开始的时候：i=0，j=N-1；
 * 以第一个数组元素作为关键数据，赋值给key，即key=A[0]；
 * 从j开始向前搜索，即由后开始向前搜索(j–)，找到第一个小于key的值A[j]，将A[j]和A[i]的值交换；
 * 从i开始向后搜索，即由前开始向后搜索(i++)，找到第一个大于key的A[i]，将A[i]和A[j]的值交换；
 * 重复第3、4步，直到i=j；(3,4步中，没找到符合条件的值，即3中A[j]不小于key,4中A[i]不大于key的时候改变j、i的值，使得j=j-1，i=i+1，直至找到为止。找到符合条件的值，进行交换的时候i， j指针位置不变。另外，i==j这一过程一定正好是i+或j-完成的时候，此时令循环结束）。
 * 
 * 
 * 例子：
 * ----------------
 * 下标 0 1 2 3 4 5
 * 数据 6 2 7 3 8 9
 * ----------------
 * 此时创建变量i=0（指向第一个数据的下标）, j=5(指向最后一个数据的下标), k=6(赋值为第一个数据的值)。
 * 我们要把所有比k小的数移动到k的左面，所以我们可以开始寻找比6小的数，从j开始，从右往左找，不断递减变量j的值，j--
 * 我们找到第一个下标3的数据比6小，于是把数据3移到下标0的位置，把下标0的数据6移到下标3，完成第一次比较：
 * ----------------
 * 下标 0 1 2 3 4 5
 * 数据 3 2 7 6 8 9
 * ----------------
 * 此时i=0 j=3 k=6
 * 接着，开始第二次比较，这次要变成找比k大的了，而且要从前往后找了,i++。
 * 递加变量i，发现下标2的数据是第一个比k大的，于是用下标2的数据7和j指向的下标3的数据的6做交换，数据状态变成下表：
 * ----------------
 * 下标 0 1 2 3 4 5
 * 数据 3 2 6 7 8 9
 * ----------------
 * 此时i=2 j=2 k=6
 * 我们称上面两次比较为一个循环。
 * 接着，再递减变量j，不断重复进行上面的循环比较。
 * 在本例中，我们进行一次循环，就发现i和j“碰头”了：他们都指向了下标2。
 * 于是，第一遍比较结束。得到结果如下，凡是k(=6)左边的数都比它小，凡是k右边的数都比它大：
 * 如果i和j没有碰头的话，就递加i找大的，还没有，就再递减j找小的，如此反复，不断循环。注意判断和寻找是同时进行的。
 * 然后，对k两边的数据，再分组分别进行上述的过程，直到不能再分组为止。
 * 至此在分别对6左右两边快速排序
 * 
 * 注意：第一遍快速排序不会直接得到最终结果，只会把比k大和比k小的数分到k的两边。
 * 为了得到最后结果，需要再次对下标2两边的数组分别执行此步骤，然后再分解数组，
 * 直到数组不能再分解为止（只有一个数据），才能得到正确结果。
 * 
 */
public class QuickSort {
    
    public static void main(String[] args) {
        int[] array = {78,69,31,43,55,10,20,88,99};

        int[] arr = Arrays.copyOf(array, array.length);
        quickSort(arr,0,arr.length - 1);
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
    }

    public static void quickSort(int[] array , int start, int end){        
        
        int i=start;
        int j=end;
        int key=array[i];//默认第一个是基准

        while(i < j)
        {
            //找小的
            while(i < j && array[j] >= key){
                j--;//当end结点值大于基准值时，向前移动，直到找到小于基准值的值
            }
            if(i < j)
            {
                array[i] = array[j];
                i++;
            }

            //找大的
            while(i < j && array[i] <= key){
                i++;
            }
            if(i < j)
            {
                array[j] = array[i];
                j--;
            }
        }

        array[i] = key;//此时start和end 已经指向同一元素

        //继续分组进行快速排序,直到
        if(i - 1 > start) {
            quickSort(array, start, i - 1);
        }
        if(j + 1 < end) {
            quickSort(array, j + 1,end);
        }
    }

}
