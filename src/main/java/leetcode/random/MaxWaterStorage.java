package leetcode.random;

public class MaxWaterStorage {

    public int maxArea(int[] height) {
        if (height.length == 0 || height.length == 1)
            return 0;
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            maxLeft[i] = i;
            maxRight[i] = i;
        }

        for (int i = 1; i < height.length; i++) {
            int left = i - 1;
            while (left >= 0) {
                if (height[left] >= height[i]) {
                    maxLeft[i] = maxLeft[left];
                    if (left == maxLeft[left])
                        left--;
                    else
                        left = maxLeft[left];
                } else {
                    left--;
                }
            }
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int right = i + 1;
            while (right < height.length) {
                if (height[right] >= height[i]) {
                    maxRight[i] = maxRight[right];
                    if (right == maxRight[right])
                        right++;
                    else
                        right = maxRight[right];
                } else {
                    right++;
                }
            }
        }

        int result = Integer.MIN_VALUE;

        for (int i = 0; i < height.length; i++) {
            if (maxLeft[i] != -1) {
                result = Math.max(result, height[i] * (maxRight[i] - maxLeft[i]));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MaxWaterStorage mx = new MaxWaterStorage();
        int[] height = {1,1};
        int result = mx.maxArea(height);

        System.out.println(result);
    }
}
