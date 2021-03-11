package leetcode.datastructures;

import java.util.ArrayList;
import java.util.List;

public class KthLargestStream {

    List<Integer> heap = new ArrayList<>();
    int k;
    Integer latestResult = null;

    public KthLargestStream(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {

        if (heap.size() == k){
            if (val > heap.get(0)) {
                heap.remove(0);
                heap.add(0, val);
                heapifyDown(0);
                return heap.get(0);
            }
            return heap.get(0);
        }
        heap.add(val);
        heapifyUp(heap.size()-1);
        return heap.get(0);
    }

    void heapifyUp(int index) {
        if (index != 0) {
            int parentIndex = getParentIndex(index);
            if (heap.get(index) < heap.get(parentIndex)) {
                swap(parentIndex, index);
                heapifyUp(parentIndex);
            }
        }
    }

    private int getParentIndex(int index) {
        int parentIndex;
        if (index % 2 == 0) {
            parentIndex = (index - 2) / 2;
        } else {
            parentIndex = (index - 1) / 2;
        }
        return parentIndex;
    }

    private void swap(int parentIndex, int index) {
        int parentTemp = heap.get(parentIndex);
        heap.add(parentIndex, heap.get(index));
        heap.remove(parentIndex + 1);
        heap.add(index, parentTemp);
        heap.remove(index + 1);
    }

    void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heap.size() && heap.get(leftChild) < heap.get(index)) {
            if (rightChild < heap.size()) {
                if (heap.get(leftChild) <= heap.get(rightChild)) {
                    swap(index, leftChild);
                    heapifyDown(leftChild);
                    return;
                }
            }else {
                swap(index, leftChild);
                heapifyDown(leftChild);
                return;
            }
        }
        if (rightChild < heap.size() &&
                heap.get(rightChild) < heap.get(index) &&
                heap.get(rightChild) <= heap.get(leftChild)) {
            swap(index, rightChild);
            heapifyDown(rightChild);
        }
    }

    public static void main(String[] args) {

        int[] input = {4, 5, 8, 2};
        int k = 3;
        KthLargestStream ks = new KthLargestStream(k, input);

        System.out.println(ks.add(3));
        System.out.println(ks.add(5));
        System.out.println(ks.add(10));
        System.out.println(ks.add(9));
        System.out.println(ks.add(4));
    }

}
