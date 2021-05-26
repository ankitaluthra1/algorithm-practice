package leetcode.glassdoorQuestions.microsoft;

public class SortAlmostSorted {

    void almostSorted(int[] arr) {
        int startingPos = -1;
        int endingPos = -1;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                startingPos = i;
                break;
            }
        }
        if (startingPos == -1)
            System.out.println("yes");
        endingPos = checkForSwap(startingPos, arr);
        boolean swapFlag = false;
        if (endingPos != -1) {
            swapFlag = true;
        }
        if (swapFlag) {
            boolean flagSwapWorked = true;
            swap(startingPos, endingPos, arr);
            if (startingPos > 0) {
                if (arr[startingPos] < arr[startingPos - 1]) {
                    flagSwapWorked = false;
                }
            }

            for (int i = startingPos; i < arr.length - 1; i++) {
                if (arr[i + 1] < arr[i]) {
                    flagSwapWorked = false;
                    break;
                }
            }
            if (flagSwapWorked) {
                System.out.println("yes");
                System.out.println("swap " + (startingPos + 1) + " " + (endingPos + 1));
                return;
            }
            swap(startingPos,endingPos,arr);
        }

        endingPos = getEndingPos(startingPos, arr);
        swap(startingPos, endingPos, arr);
        if (startingPos > 0) {
            if (arr[startingPos] < arr[startingPos - 1]) {
                System.out.println("no");
                return;
            }
        }

        for (int i = endingPos; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                System.out.println("no");
                return;
            }
        }
        System.out.println("yes");
        System.out.println("reverse " + (startingPos + 1) + " " + (endingPos + 1));
    }

    private int checkForSwap(int startingPos, int[] nums) {
        int endingPos = -1;
        if (startingPos == nums.length - 1) {
            return -1;
        }
        if (startingPos == nums.length - 2) {
            return startingPos + 1;
        }
        for (int i = startingPos; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                endingPos = i + 1;
            }
        }
        return endingPos;
    }

    private void swap(int startingPos, int endingPos, int[] nums) {
        int temp = nums[startingPos];
        nums[startingPos] = nums[endingPos];
        nums[endingPos] = temp;
    }

    private int getEndingPos(int startingPos, int[] arr) {
        for (int i = startingPos; i < arr.length - 1; i++) {
            if (arr[i + 1] > arr[i]) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public static void main(String[] args) {

        SortAlmostSorted sm = new SortAlmostSorted();
        int[] arr = {1,2,4,3,5,6};
        sm.almostSorted(arr);

    }

}
