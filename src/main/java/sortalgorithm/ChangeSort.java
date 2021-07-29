package sortalgorithm;

import org.bouncycastle.jce.provider.JDKDSASigner.stdDSA;

/**
 * 选择排序：
 * 
 * 无论什么数据进去都是 O(n²) 的时间复杂度
 * 最好情况时间复杂度:O(n²)
 * 最坏情况时间复杂度:O(n²) 
 * 空间复杂度 O(1)
 * 举个例子,序列5 8 5 2 9,我们知道第一遍选择第1个元素5会和2交换,
 * 那么原序列中两个5的相对前后顺序就被破坏了,所以选择排序是一个不稳定的排序算法。
 * 
 * 算法步骤:
 * 首先在未排序序列中找到最小（大）元素,存放到已排序序列的起始位置,
 * 再从剩余未排序元素中继续寻找最小（大）元素,然后放到已排序序列的末尾位置,
 * 重复第二步,直到所有元素均排序完毕。
 */
public class ChangeSort {
    public static void main(String[] args) {
        int[] array = {9, 7, 3, 5, 6, 1, 8, 4};
        sortChange(array);

        System.out.println("------------------------");

        //该优化在以下数组时有问题。
        int[] array1 = {9, 7, 3, 5, 6, 1, 8, 4};
        sortChangeFix(array1);
    }

    public static void sortChange(int[] array){
        int len = array.length;
        //最小值的下标
        int minIndex;
        //存放最小值
        int temp;

        //总遍历趟数len - 1
        for (int i = 0; i < len - 1; i++) {
            System.out.println("第"+(i+1)+"轮：");
            
            //默认下标为i的值是最小值
            minIndex = i;

            //从i+1开始查找最小值,并记录下标到minIndex
            for (int j = i+1; j < len; j++) {
                if(array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
            //交换minIndex最小值下标与最前面已排序序列末尾下标值
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;

            System.out.println(i+","+minIndex+"交换后：");
            for (int k : array) {
                System.out.print(k+" ");
            }
            System.out.println();
        }        
    }

    /**
     * 优化:双查找
     * @param array
     */
    public static void sortChangeFix(int[] array){
        int len = array.length;
        int left=0, right=len-1, temp;

        while(left < right) {
            System.out.println("第"+(left+1)+"轮：");
            
            int minIndex = left;
            int maxIndex = right;
            for (int i = left+1; i <= right; i++) {
                if(array[i] < array[minIndex]){
                    minIndex = i;
                }
                if(array[i] > array[maxIndex]){
                    maxIndex = i;
                }
            }
            //考虑修正的情况，最大值在最小位置，最小值在最大位置。
            temp = array[maxIndex];
            array[maxIndex] = array[right];
            array[right] = temp;

            if(minIndex == right){
                minIndex = maxIndex;
            }
            temp = array[minIndex];
            array[minIndex] = array[left];
            array[left] = temp;

            left++;
            right--;

            System.out.println((left-1)+","+minIndex+"交换后：");
            System.out.println((right+1)+","+maxIndex+"交换后：");
            for (int k : array) {
                System.out.print(k+" ");
            }
            System.out.println();
        }
    }
}
