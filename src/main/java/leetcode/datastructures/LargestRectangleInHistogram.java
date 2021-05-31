package leetcode.datastructures;

public class LargestRectangleInHistogram {

    int result = Integer.MIN_VALUE;
    int[] maxLeft;
    int[] maxRight;

    public int largestRectangleArea(int[] heights) {
        maxLeft = new int[heights.length];
        maxRight = new int[heights.length];

        maxLeft[0] = -1;
        maxRight[heights.length - 1] = heights.length;

        int index = 1;
        while (index < heights.length) {
            int left = index - 1;
            while (left >= 0 && heights[left] >= heights[index]) {
                left = maxLeft[left];
                System.out.println("Left: " + left);
            }
            maxLeft[index] = left;
            index++;
        }

        index = heights.length - 2;

        while (index >= 0) {
            int right = index + 1;
            while (right < heights.length && heights[right] >= heights[index]) {
                right = maxRight[right];
                System.out.println("Right: " + right);
            }
            maxRight[index] = right;
            index--;
        }

        for (int i = 0; i < heights.length; i++) {
            result = Math.max((maxRight[i] - maxLeft[i] - 1) * heights[i], result);
        }

        return result;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram lr = new LargestRectangleInHistogram();
        int[] heights = {2,4};
        int result = lr.largestRectangleArea(heights);

        System.out.println(result);
    }

}
