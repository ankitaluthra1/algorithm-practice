package datastructures;

import java.util.ArrayList;
import java.util.List;

public class DecodeString {

    public String decodeString(String input) {

        List<String> stack = new ArrayList<>();

        for (char ch : input.toCharArray()) {
            if (ch == ']') {
                StringBuilder sg = new StringBuilder();
                while (true) {
                    String current = stack.remove(stack.size() - 1);
                    if (current.equals("["))
                        break;
                    sg.insert(0, current);
                }
                StringBuilder repetitionString = new StringBuilder();
                while (!stack.isEmpty() && stack.get(stack.size()-1).toCharArray()[0] < 65){
                    repetitionString.insert(0,stack.remove(stack.size()-1).toCharArray()[0]);
                }
                int repetition = Integer.parseInt(repetitionString.toString());
                StringBuilder decodedPart = new StringBuilder();
                decodedPart.append(String.valueOf(sg).repeat(Math.max(0, repetition)));
                stack.add(decodedPart.toString());
            }else {
                stack.add(ch+"");
            }
        }

        StringBuilder finalString = new StringBuilder();
        for (String s: stack){
            finalString.append(s);
        }
        return finalString.toString();
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        String output = ds.decodeString("3[a2[c]]");
        System.out.println(output);
    }

}
