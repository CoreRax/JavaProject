package com.corerax.quicksort;

/**
 * Created by chengchuan on 2015/12/23.
 */
public class QuickSort {
    /**
     * 分区方法
     */
    private int getMid(int[] data, int low, int high){
        /**
         * 获取数组第一个元素作为分割点
         */
        int temp = data[low];
        /**
         * 开始比较
         */
        while (low < high){
            //先从右边开始,寻找小于分割点对应的元素
            while (low < high && data[high] >= temp){
                high--;
            }
            //找到了小于temp的元素
            if (low < high){
                data[low] = data[high];
                low++;
            }

            //从左往右，寻找大于分割点对应的元素
            while (low < high && data[low] < temp){
                low++;
            }
            if (low < high){
                data[high] = data[low];
                high--;
            }
        }

        //设置中轴位置的值
        data[low] = temp;
        //返回中轴的位置
        return low;
    }

    /**
     * 分治排序
     */
    public void sortData(int[] data, int low, int high){
        if (low < high){
            int mid = getMid(data, low, high);
            sortData(data, low, mid-1);
            sortData(data, mid+1, high);
        }
    }
}
