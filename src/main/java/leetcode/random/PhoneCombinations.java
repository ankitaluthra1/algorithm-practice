package leetcode.random;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import java.util.*;
import java.util.stream.Collectors;

class PhoneCombinations {
    Map<Integer, List<String>> numberMappings;

    public List<String> letterCombinations(String digits) {
        numberMappings = new HashMap<>();
        numberMappings.put(0, Arrays.asList(""));
        numberMappings.put(1, Arrays.asList(""));
        numberMappings.put(2, Arrays.asList("a", "b", "c"));
        numberMappings.put(3, Arrays.asList("d", "e", "f"));
        numberMappings.put(4, Arrays.asList("g", "h", "i"));
        numberMappings.put(5, Arrays.asList("j", "k", "l"));
        numberMappings.put(6, Arrays.asList("m", "n", "o"));
        numberMappings.put(7, Arrays.asList("p", "q", "r", "s"));
        numberMappings.put(8, Arrays.asList("t", "u", "v"));
        numberMappings.put(9, Arrays.asList("w", "x", "y", "z"));

        List<String> combinations = new ArrayList<>();
        return getCombinations(digits, combinations);
    }

    private List<String> getCombinations(String digits, List<String> combinations) {

        if (digits.isEmpty())
            return combinations;

        List<String> currentChars = numberMappings.get(Integer.parseInt(digits.substring(0, 1)));

        List<String> newCombinations = new ArrayList<>();
        if (combinations.isEmpty()){
            newCombinations.addAll(currentChars);
        }else {
            for (String combination: combinations){
                for (String s: currentChars){
                    newCombinations.add(combination+s);
                }
            }
        }

        return getCombinations(digits.substring(1), newCombinations);
    }

    public static void main(String[] args) {
        PhoneCombinations ph = new PhoneCombinations();
        List<String> strings = ph.letterCombinations("2");

        for (String s : strings){
            System.out.println(s);
        }
    }
}