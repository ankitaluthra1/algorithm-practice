package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingSubsequence {

    Map<Integer, Integer> cache = new HashMap<>();

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0)
            return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            Integer temp = getLongestIncreasingSequenceFor(i, nums);
            max = Math.max(max, temp);
        }
        return max;
    }

    private int getLongestIncreasingSequenceFor(int index, int[] nums) {
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        if (index == nums.length - 1) {
            return 1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] <= nums[index]) {
                continue;
            }
            int temp = getLongestIncreasingSequenceFor(i, nums);
            max = Math.max(max, temp + 1);
        }
        if (max == Integer.MIN_VALUE) {
            max = 1;
        }
        cache.put(index, max);
        return max;
    }

    public static void main(String[] args) {

        int[] input = {4,10,4,3,8,9};
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int result = lis.lengthOfLIS(input);

        System.out.println(result);

    }

}
