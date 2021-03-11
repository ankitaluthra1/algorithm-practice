package leetcode.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationInString {
    public boolean checkInclusion(String permutation, String inputString) {

        Map<Character, Integer> permMap = new HashMap<>();
        for (int i = 0; i < permutation.length(); i++) {
            Character ch = permutation.charAt(i);
            int count = permMap.getOrDefault(ch, 0);
            permMap.put(ch, count + 1);
        }

        Map<Character, Integer> outputMap = new HashMap<>();
        int permLength = permutation.length();
        List<Character> output = new ArrayList<>();
        for (int i = 0; i < inputString.length(); i++) {

            if (output.size() == permLength)
                break;
            char currentChar = inputString.charAt(i);
            int currCount = permMap.getOrDefault(currentChar, 0);
            if (currCount > 0) {
                int outputCount = outputMap.getOrDefault(currentChar, 0);
                if (outputCount < currCount) {
                    output.add(currentChar);
                    outputMap.put(currentChar, outputCount + 1);
                } else {
                    while (output.get(0) != currentChar) {
                        Character key = output.remove(0);
                        int tempCount = outputMap.get(key);
                        outputMap.put(key, tempCount -1);
                    }
                    output.remove(0);
                    output.add(currentChar);
                    //outputMap.put(currentChar, outputCount + 1);
                }

            } else {
                output = new ArrayList<>();
                outputMap = new HashMap<>();
            }

        }

        return output.size() == permLength;
    }

    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "ollooheoeh";
        PermutationInString pm = new PermutationInString();
        boolean result = pm.checkInclusion(s1, s2);
        System.out.println(result);
    }
}
