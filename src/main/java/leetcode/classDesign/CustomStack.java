package leetcode.classDesign;

import java.util.*;

public class CustomStack {
    class Element {
        private int value;
        boolean isDeleted;

        Element(int value) {
            this.value = value;
            this.isDeleted = false;
        }
    }

    List<Element> list;
    int maxSize;
    int popIndex;
    int[] inc;

    public CustomStack(int maxSize) {
        this.list = new ArrayList<>(maxSize);
        this.maxSize = maxSize;
        this.popIndex = -1;
        inc = new int[maxSize];
    }

    public void push(int value) {
        if(popIndex == maxSize-1)
            return;
        this.list.add(new Element(value));
        popIndex++;
    }

    public int pop() {
        if (popIndex == -1)
            return -1;
        Element element = this.list.get(popIndex);
        int result = element.value + inc[popIndex];
        inc[popIndex] = 0;
        list.remove(popIndex);
        popIndex--;
        return result;
    }

    public void increment(int k, int val) {
        int minValue = Math.min(k, popIndex+1);
        for (int i = 0; i < minValue; i++)
            inc[i] = inc[i] + val;
    }
}
