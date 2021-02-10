package practice.walmart;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairsForSum {

    void printPairs(int arr[], int sum) {

        Arrays.sort(arr);

        int start = 0;
        int end = arr.length - 1;
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        while (end > start) {
            if (arr[start] + arr[end] == sum) {
                pairs.add(new Pair(arr[start], arr[end]));
                start++;
                end--;
            }
            if (arr[start] + arr[end] > sum) {
                end--;
            }
            if (arr[start] + arr[end] < sum) {
                start++;
            }
        }

        pairs.stream().forEach(
                p-> System.out.print("("+p.getFirst()+","+p.getSecond()+") ")
        );

    }

    public static void main(String[] args) {
        PairsForSum ps = new PairsForSum();
        int[] arr = new int[]{1, 5, 7, -1, 5};
        ps.printPairs(arr, 6);
    }

}
