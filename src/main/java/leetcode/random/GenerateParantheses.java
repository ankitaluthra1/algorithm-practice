package leetcode.random;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/generate-parentheses/
public class GenerateParantheses {
    List<String> validParenthesis;

    public List<String> generateParenthesis(int n) {
        validParenthesis = new ArrayList<>();
        generate("", n);
        return validParenthesis;
    }

    private boolean isValidParenthesis(String currentString) {
        List<Character> stack = new ArrayList<>();
        for (int i = 0; i < currentString.length(); i++) {
            if (currentString.charAt(i) == '(')
                stack.add('(');
            else {
                if (stack.isEmpty())
                    return false;
                stack.remove(stack.size() - 1);
            }
        }
        return stack.isEmpty();
    }

    void generate(String previous, int leftover) {
        if (leftover == 0) {
            if (isValidParenthesis(previous))
                validParenthesis.add(previous);
            return;
        }
        int currentLeftOver = leftover - 1;
        generate(previous+"((", currentLeftOver);
        generate(previous+"))", currentLeftOver);
        generate(previous+"()", currentLeftOver);
        generate(previous+")(", currentLeftOver);
    }

    public static void main(String[] args) {
        GenerateParantheses gp = new GenerateParantheses();
        List<String> result = gp.generateParenthesis(3);

        for (String s : result)
            System.out.println(s);
    }

}
