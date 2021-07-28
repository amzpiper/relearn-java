package sortalgorithm;

public class InsertSort {
    
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
