package random;

import java.util.*;

public class WordBreak {

    public boolean wordBreak(String input, List<String> wordDict) {

        String current = input;

        while (current.length() > 0) {
            boolean wordExists = false;
            for (String word : wordDict) {
                if (word.length() <= current.length()) {
                    boolean found = true;
                    for (int i = 0; i < word.length(); i++) {
                        if (current.charAt(i) != word.charAt(i)) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        current = current.substring(word.length());
                        wordExists = true;
                        break;
                    }
                }
            }
            if (!wordExists)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {

        WordBreak wb = new WordBreak();
        List<String> dict = Arrays.asList("cats", "dog", "sand", "cat");
        boolean result = wb.wordBreak("catsanddog", dict);

        System.out.println(result);

    }

}
