package sortalgorithm;

/**
 * 平均时间复杂度：O(n²)
 * 最好情况时间复杂度：O(n)
 * 最快情况时间复杂度：O(n²) 
 * 空间复杂度：O(1)
 * 
 * 算法步骤：
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 */
public class maopao {
     public static void main(String[] args) {
        int[] array = {78,69,55,43,31,20,10};
        sortMaopao(array);
     }
     public static void sortMaopao(int[] array){
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            System.out.println("第"+(i+1)+"轮：");
            for (int j = 0; j < len - i -1; j++) {
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
     
}
