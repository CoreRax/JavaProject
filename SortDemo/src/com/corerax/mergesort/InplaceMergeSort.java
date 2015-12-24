package com.corerax.mergesort;

/**
 * 实现原地归并的归并排序，新建了一个辅助数组
 * Created by chengchuan on 2015/12/24.
 */
public class InplaceMergeSort {
    public void merge(int[] a){
        int[] aux = new int[10];
        //设置低位为0
        int lo = 0;
        //设置高位
        int hi = a.length-1;
        //设置中位
        int mid = hi/2 + 1;

        /**
         * 设置左右数组开始的位置
         */
        int i = lo;
        int j = mid;
        /**
         * 将内容复制到一个新的数组
         */
        for (int k = lo; k < hi; k++){
              aux[k] = a[k];
        }

        /**
         * 归并
         */
        for (int k = lo; k <hi; k++){
            if (i>mid){
                a[k] = aux[j++];
            }
            else if (j>hi){
                a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])){
                a[k] = aux[j++];
            }
            else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(int a, int b){
        if (a <= b){
            return true;
        }
        return false;
    }
}
