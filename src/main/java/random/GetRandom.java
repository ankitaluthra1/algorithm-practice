package random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class RandomizedSet {

    private final Map<Integer, Integer> data;
    int[] dataKeys;
    int index;

    public RandomizedSet() {
        int limit = 100000;
        data = new HashMap<>(limit);
        dataKeys = new int[limit];
        index = 0;
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (data.containsKey(val))
            return false;
        data.put(val, index);
        dataKeys[index] = val;
        index++;
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (data.containsKey(val)){
            int currentIndex = data.remove(val);
            if (currentIndex != index -1) {
                dataKeys[currentIndex] = dataKeys[index - 1];
                data.put(dataKeys[currentIndex], currentIndex);
            }
            index--;
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int random = new Random().nextInt(index);
        return dataKeys[random];
    }
}

public class GetRandom {

    public static void main(String[] args) {

        RandomizedSet rm = new RandomizedSet();

        System.out.println(rm.remove(0));
        System.out.println(rm.remove(0));
        System.out.println(rm.insert(0));

        System.out.println(rm.getRandom());

        System.out.println(rm.remove(0));
        System.out.println(rm.insert(0));

    }
}
