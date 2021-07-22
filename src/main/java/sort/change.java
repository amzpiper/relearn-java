package sort;

/**
 * 无论什么数据进去都是 O(n²) 的时间复杂度
 * 算法步骤
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 重复第二步，直到所有元素均排序完毕。
 */
public class change {
    public static void main(String[] args) {
        int[] array = {78,69,55,43,31,20,10};
        sortChange(array);
    }
    public static void sortChange(int[] array){
        int len = array.length;
        int minIndex,temp;
        for (int i = 0; i < len - 1; i++) {
            System.out.println("第"+(i+1)+"轮：");
            
            minIndex = i;
            for (int j = i+1; j < len; j++) {
                if(array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
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
}
