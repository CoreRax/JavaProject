import com.corerax.mergesort.InplaceMergeSort;
import com.corerax.quicksort.QuickSort;
import com.corerax.quicksort.QuickSortDemo;

/**
 * Created by chengchuan on 2015/12/23.
 */
public class Test {
    public static void main(String[] args) {
        //需要排序的内容，以数组的形式存储
        int[] data = {4,2,5,5,3,7,1,9,10,8};
        InplaceMergeSort test = new InplaceMergeSort();
        test.merge(data);
        for(int i=0;i<data.length;i++){
            System.out.print(data[i]+" ");
        }
    }
}
