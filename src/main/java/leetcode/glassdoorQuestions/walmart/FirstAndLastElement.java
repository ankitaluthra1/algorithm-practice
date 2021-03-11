package leetcode.glassdoorQuestions.walmart;

public class FirstAndLastElement {

    int[] nums;
    int target;

    public int[] searchRange(int[] nums, int target) {
        this.nums = nums;
        this.target = target;

        if (nums.length == 0)
            return new int[]{-1,-1};

        int index = search(0, nums.length-1);

        if (index == -1) {
            return new int[]{-1, -1};
        }
        int first = index;
        int last = index;

        while (first > 0) {
            if (nums[first - 1] != target)
                break;
            first--;
        }
        while (last < nums.length-1) {
            if (nums[last + 1] != target)
                break;
            last++;
        }

        return new int[]{first, last};
    }

    private int search(int start, int end) {
        if (end < start)
            return -1;
        if (end == start) {
            if (nums[end] == target)
                return end;
            return -1;
        }
        int mid = (end + start) / 2;
        if (nums[mid] == target)
            return mid;
        if (target > nums[mid])
            return search(mid + 1, end);
        else
            return search(start, mid - 1);
    }

    public static void main(String[] args) {
        int[] nums = {2,2,2};
        int target = 2;
        FirstAndLastElement fl = new FirstAndLastElement();
        int[] result = fl.searchRange(nums, target);

        System.out.println(result[0]+"  "+result[1]);
    }

}
