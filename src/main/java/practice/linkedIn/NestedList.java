package practice.linkedIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger {
    List<NestedInteger> list;
    Integer value;

    public NestedInteger() {
        list = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.value = value;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return (list == null);
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
// Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return this.value;
    }

    public void add(NestedInteger ni) {
        this.list.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
// Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return this.list;
    }
}

public class NestedList {

    public int depthSum(List<NestedInteger> nestedList) {

        int sum = 0;
        for (NestedInteger i : nestedList) {
            sum = sum + getNestedSum(i, 1);
        }
        return sum;
    }

    public int getNestedSum(NestedInteger nestedList, int level) {

        if (nestedList.isInteger())
            return nestedList.getInteger() * level;
        else{
            int sum = 0;
            for (NestedInteger n: nestedList.getList()){
                sum = sum +getNestedSum(n,level + 1);
            }
            return sum;
        }

    }

    public static void main(String[] args) {

        NestedInteger n1 = new NestedInteger(4);
        NestedInteger n2 = new NestedInteger(5);
        NestedInteger n3 = new NestedInteger();
        n3.add(new NestedInteger(2));
        NestedInteger n4 = new NestedInteger();
        NestedInteger n5 = new NestedInteger();
        n5.add(new NestedInteger(1));
        n4.add(n5);

        NestedList list = new NestedList();
        int sum = list.depthSum(Arrays.asList(n1,n2,n3,n4));

        System.out.println(sum);
    }

}
