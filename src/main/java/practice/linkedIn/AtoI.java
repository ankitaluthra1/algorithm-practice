package practice.linkedIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtoI {

    int covertStringToInt(String s){

        int result = 0;

        int asciiCodeStart = 48;

        s = s.replaceAll(" ","");

        List<Integer> digits = new ArrayList<>();

        int start = 0;
        if (s.charAt(0) == '-')
            start = 1;
        for (int i = start; i < s.length(); i++){
            if (s.charAt(i) > 47 && s.charAt(i) < 58)
                digits.add(s.charAt(i) - asciiCodeStart);
        }

        int power = digits.size() - 1;

        for (int digit : digits){
            result = (int) (result + (digit * Math.pow(10, power)));
            power--;
        }

        if (start == 1)
            return  -result;

        return result;
    }

    public static void main(String[] args) {
        AtoI atoi = new AtoI();

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        int result = atoi.covertStringToInt(input);

        System.out.println(result);
    }
}
