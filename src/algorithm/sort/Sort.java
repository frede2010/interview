package algorithm.sort;

/**
 * @author furui
 * @date 2019/3/5 0005
 */
public class Sort {

    /**
     * 冒泡排序
     * 稳定排序，时间复杂度：O(n^2）
     */
    public static void dobbleSort(int[] nums){
        int length = nums.length;
        for (int i = 0; i < length - 1; i++){
            for (int j = 0; j < length - 1; j++) {
                if (nums[j] > nums [j+1]){
                    nums[j] = nums[j]^nums[j+1];
                    nums[j+1] = nums[j]^nums[j+1];
                    nums[j] = nums[j]^nums[j+1];
                }
            }
        }
    }

    /**
     * 快速排序
     * 不稳定排序，时间复杂度：O(nlogn)
     * 选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,
     * 一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
     * 然后再用同样的方法递归地排序划分的两部分。
     * @param nums
     */
    public static void quickSort(int[] nums){
        int middle = middle(nums, 0, nums.length - 1);
        middle(nums, 0, middle - 1);
        middle(nums, middle + 1, nums.length - 1);
    }

    /**
     *
     * @param numbers 数组
     * @param low
     * @param high
     * @return
     */
    private static int middle(int[] numbers, int low, int high){
        int base = numbers[low];
        while (low < high) {
            while (low < high && base <= numbers[high]) {
                high--;
            }
            numbers[low] = numbers[high];
            while (low < high && base >= numbers[low]){
                low ++;
            }
            numbers[high] = numbers[low];
        }
        numbers[low] = base;
        return low;
    }

    /**
     * 插入排序
     * 稳定排序，时间复杂度：O(n^2)<br>
     * 在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排
     * 好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
     * 也是排好顺序的。如此反复循环，直到全部排好顺序。
     * @param nums
     */
    public static void insertSort(int[] nums){
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int temp = nums[i];
            int j = i-1;
            for (;j >=0 && temp < nums[j];j--){
                nums[j+1] = nums[j];
            }
            nums[j+1] = temp;
        }
    }

    /**
     * 选择排序-1.简单选择排序<br>
     * 不稳定排序<br>
     * 时间复杂度：O(n^2)<br>
     * 在要排序的一组数中，选出最小（或者最大）的一个数与第1个位置的数交换；
     * 然后在剩下的数当中再找最小（或者最大）的与第2个位置的数交换，
     * 依次类推，直到第n-1个元素（倒数第二个数）和第n个元素（最后一个数）比较为止。
     */
    public static void selectSort(int[] nums) {
        int length = nums.length;
        for (int i = 0;i < length;i++){
            int index = i;
            for (int j = i+1;j < length;j++){
                if (nums[index] > nums[j]) {
                    index = j;
                }
            }
            if (i != index) {
                nums[i] = nums[i] ^ nums[index];
                nums[index] = nums[i] ^ nums[index];
                nums[i] = nums[i] ^ nums[index];
            }
        }
    }
}
