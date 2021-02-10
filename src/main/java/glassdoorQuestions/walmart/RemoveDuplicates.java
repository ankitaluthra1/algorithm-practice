package glassdoorQuestions.walmart;

import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicates {

    static String removeDuplicates(String input) {
        List<Character> stack = new ArrayList<>();

        for (Character c : input.toCharArray()) {
            if (stack.isEmpty()) {
                stack.add(c);
                continue;
            }
            if (stack.get(stack.size() - 1) == c) {
                stack.remove(stack.size() - 1);
            } else
                stack.add(c);
        }

        StringBuilder sg = new StringBuilder();

        for (Character c : stack) {
            sg.append(c);
        }
        return sg.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates("geeksforgeeg"));
    }

}
