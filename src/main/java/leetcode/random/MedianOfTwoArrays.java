package leetcode.random;

//https://leetcode.com/problems/median-of-two-sorted-arrays/
public class MedianOfTwoArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0){
            if((nums2.length - 1) % 2 == 0)
                return nums2[midForEven(nums2.length - 1)];
            else {
                int index = midForOdd(nums2.length - 1);
                return (double)(nums2[index] + nums2[index+1]) / 2;
            }
        }

        if (nums2.length == 0){
            if((nums1.length - 1) % 2 == 0)
                return nums1[midForEven(nums1.length - 1)];
            else {
                int index = midForOdd(nums1.length - 1);
                return (double) (nums1[index] + nums1[index+1]) / 2;
            }
        }


        int total = nums1.length + nums2.length - 1;
        int index;
        boolean even = false;
        if(total% 2 == 0)
            index = midForEven(total);
        else {
            even = true;
            index = midForOdd(total);
        }

        int currIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        int[] arr = new int[index+2];

        while (leftIndex < nums1.length && rightIndex < nums2.length && currIndex <= index+1){

            if (nums1[leftIndex] < nums2[rightIndex]) {
                arr[currIndex] = nums1[leftIndex];
                leftIndex++;
                currIndex++;
            }
            else {
                arr[currIndex] = nums2[rightIndex];
                rightIndex++;
                currIndex++;
            }
        }

        if (currIndex <= index+1){
            while (leftIndex < nums1.length && currIndex <= index+1){
                arr[currIndex] = nums1[leftIndex];
               currIndex++;
               leftIndex++;
            }
            while (rightIndex < nums2.length && currIndex <= index+1){
                arr[currIndex] = nums2[rightIndex];
                currIndex++;
                rightIndex++;
            }
        }

        if (even){
            return (double) (arr[index] + arr[index + 1]) / 2;
        }

        return arr[index];

    }

    int midForEven(int length){
      return length / 2;
    }

    int midForOdd(int length){
        return length / 2;
    }

    public static void main(String[] args) {
        MedianOfTwoArrays md = new MedianOfTwoArrays();
        int[] nums1 = {8,1};
        int[] nums2 = {};
        double result = md.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
