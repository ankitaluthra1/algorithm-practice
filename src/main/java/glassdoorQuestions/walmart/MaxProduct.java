package glassdoorQuestions.walmart;

import java.util.ArrayList;
import java.util.List;

public class MaxProduct {

    public int maxProduct(int[] nums) {

        if (nums.length < 2)
            throw new IllegalArgumentException("Length less than 2");

        if (nums.length == 2) {
            return (nums[0] - 1) * (nums[1] - 1);
        }


        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0)
                list1.add(nums[i]);
            else
                list2.add(nums[i]);
        }

        int[] arr1 = list1.stream().mapToInt(i -> Math.abs(i)).sorted().toArray();
        int[] arr2 = list2.stream().sorted().mapToInt(i -> i).toArray();

        int max1 = Integer.MIN_VALUE;
        if (arr1.length > 1) {
            int product = (arr1[arr1.length - 1] - 1) * (arr1[arr1.length - 2] - 1);
            max1 = Math.max(max1, product);
        }

        int max2 = Integer.MIN_VALUE;
        if (arr2.length > 1) {
            int product = (arr2[arr2.length - 1] - 1) * (arr2[arr2.length - 2] - 1);
            max2 = Math.max(max2, product);
        }

        return Math.max(max1, max2);
    }

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        int[] nums = {3,7};
        int result = maxProduct.maxProduct(nums);

        System.out.println(result);
    }

}
