package algorithm.sort;

/**
 * @author furui
 * @date 2019/3/6 0006
 */
public class Test {
    public static void main(String[] args) {
        int[] nums = {2,1,3,6,5,10,12,8,11,9,0};
//        Sort.dobbleSort(nums);
//        Sort.quickSort(nums);
//        Sort.insertSort(nums);
        Sort.selectSort(nums);
        printArray(nums);
    }

    private static void printArray(int[] nums){
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
