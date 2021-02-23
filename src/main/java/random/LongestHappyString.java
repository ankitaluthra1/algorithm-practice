package random;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LongestHappyString {
    class CharCount {
        char c;
        int count;

        CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }

        public String getHappyString() {
            if (count < 1) {
                throw new IllegalStateException("Count cannot be less than 1");
            }

            int maxCount = count > 2 ? 2 : 1;
            this.count = this.count - maxCount;
            StringBuilder sg = new StringBuilder("");
            for (int i = 0; i < maxCount; i++) {
                sg.append(c);
            }
            return sg.toString();
        }
    }

    public String longestDiverseString(int a, int b, int c) {

        List<CharCount> list = new ArrayList<>();
        list.add(new CharCount('a', a));
        list.add(new CharCount('b', b));
        list.add(new CharCount('c', c));

        StringBuilder sg = new StringBuilder();
        list = list.stream().filter(l -> l.count > 0).collect(Collectors.toList());
        while (list.size() > 1) {
            for (CharCount ch : list) {
                sg.append(ch.getHappyString());
            }
            list = list.stream().filter(l -> l.count > 0).collect(Collectors.toList());
        }

        if (list.isEmpty())
            return sg.toString();

        return findPlaceForLeftOverChars(sg.toString(), list.get(0));

    }

    private String findPlaceForLeftOverChars(String s, CharCount charCount) {

        int start = 0;

        if (s.charAt(0) != charCount.c){
            String happyString = charCount.getHappyString();
            s = happyString + s;
            start = happyString.length();
        }

        while (charCount.count > 0 && start < s.length()){

            boolean found = false;

            for (int i = start; i < s.length();i++){
                if (s.charAt(i) != charCount.c){
                    if (i == s.length() - 1){
                        return s + charCount.getHappyString();
                    }
                    if (s.charAt(i+1) != charCount.c){
                        String current = charCount.getHappyString();
                        s = s.substring(0, i+1) + current + s.substring(i+1);
                        start = i + current.length();
                        found = true;
                        break;
                    }
                }
            }

            if (!found)
                return s;

        }

        return s;

    }

    public static void main(String[] args) {
        LongestHappyString lg = new LongestHappyString();

        String result = lg.longestDiverseString(7, 1, 0);

        System.out.println(result);
    }


}
