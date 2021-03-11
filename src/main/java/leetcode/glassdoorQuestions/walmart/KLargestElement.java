package leetcode.glassdoorQuestions.walmart;

import java.util.PriorityQueue;

public class KLargestElement {

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));

        for (int i = 0; i < nums.length; i++)
            queue.add(nums[i]);

        int result = -1;
        for (int i=0;i<k;i++){
            result = queue.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        KLargestElement kLargestElement = new KLargestElement();
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int result = kLargestElement.findKthLargest(nums, 4);
        System.out.println(result);
    }

}
