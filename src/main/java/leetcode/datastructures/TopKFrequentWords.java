package leetcode.datastructures;

// https://leetcode.com/problems/top-k-frequent-words/

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TopKFrequentWords {

    class WordFrequency implements Comparable<WordFrequency> {
        String word;
        int frequency;

        public WordFrequency(String word) {
            this.word = word;
            this.frequency = 0;
        }

        public void increase() {
            this.frequency++;
        }

        @Override
        public int compareTo(@NotNull WordFrequency o) {

            if (this.frequency == o.frequency)
                return this.word.compareTo(o.word);
            return o.frequency - this.frequency;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {

        Map<String, WordFrequency> map = new HashMap<>();

        for (String word : words) {
            WordFrequency wordFrequency = map.getOrDefault(word, new WordFrequency(word));
            wordFrequency.increase();
            map.put(word, wordFrequency);
        }

        PriorityQueue<WordFrequency> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(map.values());

        List<String> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            result.add(priorityQueue.poll().word);
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();

        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};

        List<String> result = topKFrequentWords.topKFrequent(words, 4);

        for (String s : result)
            System.out.println(s);
    }

}
