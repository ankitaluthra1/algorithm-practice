package leetcode.datastructures;

// https://leetcode.com/problems/search-in-rotated-sorted-array/

public class RotatedArraySearch {

    int[] nums;

    public int search(int[] nums, int target) {
        this.nums = nums;
        return binSearch(0, nums.length-1,target);
    }

    private int binSearch(int start, int end, int target) {
        if(end < start)
            return -1;

        int mid = (end + start) / 2;
        if(target == nums[mid])
            return mid;

        if(nums[mid] >= nums[start]){
            if(target < nums[mid] && target >= nums[start])
                return binSearch(start, mid-1, target);
            else
                return binSearch(mid+1, end,target);
        }
        if(target > nums[mid] && target <= nums[end]){
            return binSearch(mid+1, end, target);
        }
        return binSearch(start, mid-1, target);
    }

    public static void main(String[] args) {
        RotatedArraySearch rotatedArraySearch = new RotatedArraySearch();
        int[] nums = {1};
        int search = rotatedArraySearch.search(nums, 2);

        System.out.println(search);
    }

}
