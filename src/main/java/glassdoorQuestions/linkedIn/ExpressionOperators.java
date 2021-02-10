package glassdoorQuestions.linkedIn;

import java.util.*;

public class ExpressionOperators {

    List<String> output;
    int target;

    public List<String> addOperators(String num, int target) {
        output = new ArrayList<>();
        if (num.isEmpty())
            return output;
        this.target = target;
        checkAllPossibilities(num.charAt(0)+"",1, num);

        return output;

    }

    private void checkAllPossibilities(String previousString, int index, String num) {

        if (index == num.length()) {
            if (checkExpression(previousString))
                output.add(previousString);
            return;
        }

        List<String> expressions = new ArrayList<>();
        expressions.add("+");
        expressions.add("-");
        expressions.add("*");
        expressions.add("");
        for (String operator : expressions) {
            checkAllPossibilities(previousString + operator + num.charAt(index), index + 1, num);
        }

    }

    private boolean checkExpression(String previousString) {
        List<String> stack = new ArrayList<>();
        List<String> expressions = new ArrayList<>();
        expressions.add("+");
        expressions.add("-");
        expressions.add("*");
        for (Character ch : previousString.toCharArray()) {
            String curr = ch.toString();
            if (expressions.contains(curr)) {
                stack.add(curr);
            } else {
                if (!stack.isEmpty()) {
                    if (!expressions.contains(stack.get(stack.size() - 1))) {
                        String operand = stack.remove(stack.size() - 1) + curr;
                        stack.add(operand);
                    }else {
                        stack.add(curr);
                    }
                } else {
                    stack.add(curr);
                }
            }
        }
        List<String> newStack = new ArrayList<>();

        while (!stack.isEmpty()){
            String curr = stack.remove(0);
            if (curr.length() > 1 && curr.startsWith("0"))
                return false;
            if (curr.equals("*")){
                long operand1 = Long.parseLong(newStack.remove(newStack.size()  - 1));
                String curr2 = stack.remove(0);
                if (curr2.length() > 1 && curr2.startsWith("0"))
                    return false;
                long operand2 = Long.parseLong(curr2);
                long result = operand1 * operand2;
                newStack.add(result+"");
            }else {
                newStack.add(curr);
            }
        }

        if (newStack.size() == 1) {
            String s = newStack.get(0);
            if (s.length() > 1 && s.startsWith("0"))
                return false;
            return Long.parseLong(s) == target;
        }

        long result = 0;

        while (!newStack.isEmpty()){
            String curr = newStack.remove(0);
            if (curr.length() > 1 && curr.startsWith("0"))
                return false;
            if (curr.equals("+")){
                long operand = Long.parseLong(newStack.remove(0));
                result = result + operand;
                continue;
            }
            if (curr.equals("-")){
                long operand = Long.parseLong(newStack.remove(0));
                result = result - operand;
                continue;
            }
            result = Long.parseLong(curr);
        }
        return result == target;
    }

    public static void main(String[] args) {
        ExpressionOperators eo = new ExpressionOperators();
        List<String> result = eo.addOperators("3456237490",9191);

        for (String s: result){
            System.out.println(s);
        }
    }

}
