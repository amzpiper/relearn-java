package sortalgorithm;

/**
 * 冒泡排序：
 * 
 * 冒泡排序还有一种优化算法,就是立一个 flag,当在一趟序列遍历中元素没有发生交换,则证明该序列已经有序。但这种改进对于提升性能来
 * 说并没有什么太大作用
 * 
 * 平均时间复杂度:O(n²)
 * 最好情况时间复杂度:O(n)
 * 最坏情况时间复杂度:O(n²) 
 * 空间复杂度:O(1)
 * 稳定
 * 
 * 算法步骤:
 * 比较相邻的元素,如果第一个比第二个大,就交换他们两个。
 * 对每一对相邻元素作同样的工作,从开始第一对到结尾的最后一对.这步做完后,最大的数会放到最后.
 * 针对所有的元素重复以上的步骤,除了最后一个。
 * 持续每遍对越来越少的元素重复上面的步骤,直到没有任何一对数字需要比较
 */
public class MaoPaoSort {
     public static void main(String[] args) {
        int[] array = {78,69,31,43,55,10,20,88,99};
        sortMaopao(array);

        System.out.println("-------------------------------------");

        int[] array1 = {78,69,31,43,55,10,20,88,99};
        sortMaoPaoFix1(array1);
        
        System.out.println("-------------------------------------");

        int[] array2 = {78,69,31,43,55,10,20,88,99};
        sortMaoPaoFix2(array2);
     }

     public static void sortMaopao(int[] array){
        int len = array.length;
        
        //总遍数len - 1
        for (int i = 0; i < len - 1; i++) {
            System.out.println("第"+(i+1)+"轮：");
            
            //从头0到尾比较,每一遍都减少总遍历个数len - i - 1
            for (int j = 0; j < len - i - 1; j++) {
                //比较相邻2个参数,前面比后面大就交换,最大的会放到最后面
                if(array[j] > array[j+1]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;

                    System.out.println(j+","+(j+1)+"交换后：");
                    for (int k : array) {
                        System.out.print(k+" ");
                    }
                    System.out.println();             
                }
            }
        }
     }
     
     /**
      * 添加flag标志优化遍历趟数,当某一趟无交换,代表整体已经有序
      * @param array
      */
      public static void sortMaoPaoFix1(int[] array){
        int temp;
        int len = array.length;
        
        for (int i = 0; i < len - 1; i++) {
            System.out.println("第"+(i+1)+"轮：");

            int flag = 0;
            for (int j = 0; j < len - i - 1; j++) {
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = 1;

                    System.out.println(j+","+(j+1)+"交换后：");
                    for (int item : array) {
                        System.out.print(item+" ");
                    }
                    System.out.println();
                }
            }

            if(flag == 0){
                System.out.println("flag = 0：");
                for (int item : array) {
                    System.out.print(item+" ");
                }
                System.out.println();             
                return;
            }
        }
    }

    /**
      * 添加flag标志优化遍历趟数,当某一趟无交换,代表整体已经有序
      * 在每趟扫描中,记住最后一次交换发生的位置lastExchange,（该位置之后的相邻记录均已有序）。
      * 下一趟排序开始时,R[1..lastExchange-1]是无序区,R[lastExchange..n]是有序区。
      * 这样,一趟排序可能使当前无序区扩充多个记录,因此记住最后一次交换发生的位置lastExchange,从而减少排序的趟数。
      * 
      * 优化一仅仅适用于连片有序而整体无序的数据(例如：1, 2,3 ,4 ,7,6,5)。
      * 但是对于前面大部分是无序而后边小半部分有序的数据(1,2,5,7,4,3,6,8,9,10)排序效率也不可观,
      * 对于种类型数据,我们可以继续优化。
      * 既我们可以记下最后一次交换的位置,后边没有交换,必然是有序的,然后下一次排序从第一个比较到上次记录的位置结束即可
      * @param array
      */
     public static void sortMaoPaoFix2(int[] array){
        int temp;
        int len = array.length;
        int k = len - 1;
        int pos = 0;//记录循环时最后一次交换的位置
        
        for (int i = 0; i < len - 1; i++) {
            System.out.println("第"+(i+1)+"轮：");

            int flag = 0;
            for (int j = 0; j < k; j++) {
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = 1;
                    pos = j;

                    System.out.println(j+","+(j+1)+"交换后：");
                    for (int item : array) {
                        System.out.print(item+" ");
                    }
                    System.out.println();
                }
            }
            k = pos;

            if(flag == 0){
                System.out.println("flag = 0：");
                for (int item : array) {
                    System.out.print(item+" ");
                }
                System.out.println();             
                return;
            }
        }
    }
}
