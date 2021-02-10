package practice.microsoft;

public class MaximumProductSubArray {

    int maxOf(int... varargs) {
        int temp = Integer.MIN_VALUE;
        for (int i = 0; i < varargs.length; i++) {
            temp = Math.max(temp, varargs[i]);
        }
        return temp;
    }

    int minOf(int... varargs) {
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < varargs.length; i++) {
            temp = Math.min(temp, varargs[i]);
        }
        return temp;
    }

    public int maxProduct(int[] nums) {

       int min = nums[0];
       int max = nums[0];
       int result = nums[0];

       for (int i = 1; i < nums.length; i++){
        int currMin = nums[i] * min;
        int currMax = nums[i] * max;

        min = minOf(nums[i], currMin, currMax);
        max = maxOf(nums[i],currMax, currMin);

        result = maxOf(max, result);
       }
       return result;
    }

    public static void main(String[] args) {
        MaximumProductSubArray mx = new MaximumProductSubArray();
        int[] nums = {-3,-1,-1};
        int result = mx.maxProduct(nums);
        System.out.println(result);
    }

}
