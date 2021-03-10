package random;

public class DuplicateArray {

    public int removeDuplicates(int[] nums) {

        if (nums.length < 3){
            return nums.length;
        }

        int newLength = nums.length;
        int i = 0;
        while (i < newLength) {
            int current = nums[i];
            int start = i;
            while (i < newLength && nums[i] == current) {
                i++;
            }

            if (i == newLength) {
                if (i - start > 1) {
                    return start + 2;
                } else {
                    return newLength;
                }
            }

            int leftShiftLength = i - (start + 2);

            if (leftShiftLength <= 0) {
                continue;
            }

            for (int j = i; j < newLength ; j++){
                nums[j - leftShiftLength] = nums[j];
            }

            newLength = newLength - leftShiftLength;
            i = i - leftShiftLength;
        }
        return newLength;
    }

    public static void main(String[] args) {
        DuplicateArray da = new DuplicateArray();
        int[] nums = {1};
        int result = da.removeDuplicates(nums);

        System.out.println(result);

        for (int i = 0; i < result; i++){
            System.out.print(nums[i]+", ");
        }
        System.out.println();
    }

}
