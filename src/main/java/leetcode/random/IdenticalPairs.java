package leetcode.random;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdenticalPairs {

    Map<Integer, List<Integer>> indexMapping;

    public int solution(int[] input) {
        indexMapping = new HashMap<>();

        for (int i = 0; i < input.length; i++) {
            List<Integer> list = indexMapping.getOrDefault(input[i], new ArrayList<>());
            list.add(i);
            indexMapping.put(input[i], list);
        }

        BigInteger total = getTotalPairsCount();

        BigInteger max = new BigInteger("1000000000");
        if (total.compareTo(max) > 0)
            return 1000000000;

        return total.intValue();

    }

    private BigInteger getTotalPairsCount() {
        BigInteger count = new BigInteger("0");

        for (Map.Entry<Integer, List<Integer>> entry : indexMapping.entrySet()) {
            count = count.add(new BigInteger(getTotal(entry.getValue().size() - 1)+""));
        }

        return count;
    }

    private int getTotal(int i) {
        return (i * (i + 1)) / 2;
    }

    public static void main(String[] args) {
        IdenticalPairs p = new IdenticalPairs();
        int[] input = {-1, 1,-1};
        int result = p.solution(input);

        System.out.println(result);
    }


}
