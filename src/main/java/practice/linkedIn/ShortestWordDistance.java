package practice.linkedIn;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {

        int word1Pointer = -1;
        int word2Pointer = -1;

        int index = 0;
        int minDistance = Integer.MAX_VALUE;

        for (String temp: words){
            if (temp.equals(word1)) {
                word1Pointer = index;
                if (word2Pointer != -1){
                    minDistance = Math.min(minDistance, Math.abs(word1Pointer - word2Pointer));
                }
            }
            if (temp.equals(word2)) {
                word2Pointer = index;
                if (word1Pointer != -1) {
                    minDistance = Math.min(minDistance, Math.abs(word1Pointer - word2Pointer));
                }
            }
            index++;
        }

        return minDistance;
    }

    public static void main(String[] args) {

        ShortestWordDistance sh = new ShortestWordDistance();
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        int result = sh.shortestDistance(words, "coding", "makes");

        System.out.println(result);

    }

}
