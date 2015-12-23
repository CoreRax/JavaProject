package com.corerax.quicksort;

/**
 * Created by chengchuan on 2015/12/23.
 */
public class QuickSortDemo {
    public static void sort(int[] a){
        sort(a, 0, a.length-1);
    }

    private static void sort(int[] a, int lo, int hi){
        if (hi <= lo)return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private static int partition(int[] a, int lo, int hi){
        /**
         * 将数组切分为a[lo...i-1],a[i],a[i+1...hi]
         */
        int i = lo, j = hi+1;
        int v = a[lo];
        while (true){
            /**
             * 左右扫描检查扫描是否结束
             */
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(int a, int b){
        if (a <= b){
            return true;
        }
        return false;
    }

    private static  void exch(int[] a, int x ,int y){
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
}
