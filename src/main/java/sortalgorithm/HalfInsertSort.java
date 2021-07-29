package sortalgorithm;

import java.util.Arrays;

/**
 * 折半插入排序:
 * 
 * 
 */
public class HalfInsertSort {
    public static void main(String[] args) {
        int[] array = {9,8,7,6,5,4,3,2,1};
        sort(array);
    }

    public static void sort(int[] array){
        int[] arr = Arrays.copyOf(array, array.length);
        int middleIndex=0;    //m充当比较区间的中点
        int temp;           //x用来存储当前待排序的数据
        int low;            //low代表要与Array[i]进行比较的有序区间的第一个元素所在位置
        int high;           //high代表要与Array[i]进行比较的有序区间的最后一个元素所在位置

        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            low = 0;        /*第一次划分有序比较区间，比较区间的第一个元素所在位置为0*/
            high = i;     /*第一次划分有序比较区间，比较区间的最后一个元素所在位置为n-1*/
            System.out.println("第"+i+"趟：low:"+low+",high:"+(high)+",middleIndex:" + middleIndex+",temp:"+temp+",arr[i]:"+i);
            System.out.println("原始：");
            for (int item : arr) {
                System.out.print(item + " ");
            }
            System.out.println();
            /*比较查找arr[i]合适的插入位置*/
            while(low <= high){
                middleIndex = (low+high)/2;
                if(temp > arr[middleIndex]){
                    low = middleIndex+1;
                }else {
                    high = middleIndex-1;
                }
                System.out.println("low:"+low+",high:"+(high)+",middleIndex:" + middleIndex );
            }

            /*确定好位置后，将位置之后的数据后移，插入待排序数据*/
            System.out.println("确定好位置后，将位置之后的数据后移，插入待排序数据:"+"low:"+low+",high:"+(high)+",middleIndex:" + middleIndex );
            int j = i-1; 
            while(j > high){
                System.out.println((j+1) + "<==>" + (j));
                arr[j+1] = arr[j];
                j--;

                for (int item : arr) {
                    System.out.print(item + " ");
                }
                System.out.println();
            }
            System.out.println("arr[j+1]插入temp,j:"+j+",temp:"+temp);
            arr[j+1] = temp;

            for (int item : arr) {
                System.out.print(item + " ");
            }
            System.out.println();
            System.out.println();
        }

        System.out.println("结果：");
        for (int item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

}
