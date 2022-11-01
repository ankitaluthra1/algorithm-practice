package leetcode.classDesign;

import java.util.HashSet;
import java.util.Set;

public class PalindromicSubsequence {

    int countPalindromicSubsequences(String s) {

        HashSet<String> result = new HashSet<>();
        getAllSubsequences("",s, result);

        System.out.println(result);
        return 0;
    }



    private void getAllSubsequences(String start, String remaining, Set<String> result) {
        if(remaining.length() ==0) {
            result.add(start);
            return;
        }

        for(int i = 0; i < remaining.length(); i++){
            String newStart = start + remaining.charAt(i);
            result.add(newStart);
            getAllSubsequences(newStart, remaining.substring(0,i)+remaining.substring(i+1),result);
        }

    }

    public static void main(String[] args) {
        PalindromicSubsequence p = new PalindromicSubsequence();
        p.countPalindromicSubsequences("ab");
    }

}
