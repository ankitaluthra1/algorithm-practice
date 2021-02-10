package random;

import java.util.Arrays;

public class MinSubArraySum {

    static long angryChildren(int k, int[] packets) {

        int[] sortedPackets = Arrays.stream(packets).sorted().toArray();

        for (int elem: sortedPackets){
            System.out.print(elem+", ");
        }
        System.out.println();

        int start = 0;
        int end = start + k - 1;

        int unfairSum = 0;
        int minStart = start;
        int minEnd = end;

        for (int i = start; i < end; i++) {
            unfairSum = unfairSum + sortedPackets[i + 1] - sortedPackets[i];
        }

        System.out.println(unfairSum);

        int currSum = unfairSum;
        while (end < sortedPackets.length-1) {
            currSum = currSum +(sortedPackets[end+1] - sortedPackets[end]) - (sortedPackets[start+1] - sortedPackets[start]);
            if (currSum < unfairSum) {
                unfairSum = currSum;
                minStart = start+1;
                minEnd = end+1;
            }
            start++;
            end++;
        }

        System.out.println(minStart+" "+minEnd);
        unfairSum = 0;
        for (int i = minStart; i < minEnd; i++) {
            for (int j = i + 1; j <= minEnd; j++) {
                unfairSum = unfairSum + sortedPackets[j] - sortedPackets[i];
            }
        }

        return unfairSum;
    }

    public static void main(String[] args) {
        int[] packets = {4504,1520,5857,4094,4157,3902,822,6643,2422,7288,8245,9948,2822,1784,7802,3142,9739,5629,5413,7232};
        long result = angryChildren(5, packets);
        System.out.println(result);
    }


}
