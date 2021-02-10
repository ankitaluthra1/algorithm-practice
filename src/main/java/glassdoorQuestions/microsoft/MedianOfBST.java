package glassdoorQuestions.microsoft;

import java.util.ArrayList;
import java.util.List;

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class MedianOfBST {

    Node constructTree(int[] input, int index) {
        Node root = null;
        if (index < input.length) {
            Node left = constructTree(input, 2 * index + 1);
            Node right = constructTree(input, 2 * index + 2);
            root = new Node(input[index], left, right);
        }
        return root;
    }

    int count(Node root) {
        if (root == null)
            return 0;

        int leftCount = count(root.left);
        int rightCount = count(root.right);
        return 1 + leftCount + rightCount;
    }

    //Morris algo...didnt understand :(
    int median(Node root, List<Integer> medianPlaces, List<Integer> medianValues, int currCount) {
        if (root == null)
            return 0;

        int leftCount = median(root.left, medianPlaces, medianValues, currCount);
        int updatedCurrCount = 1 + leftCount;
        if (medianPlaces.contains(currCount)) {
            medianValues.add(root.value);
        }
        int rightCount = median(root.right, medianPlaces, medianValues, updatedCurrCount);
        return rightCount;
    }

    public static void main(String[] args) {

        MedianOfBST md = new MedianOfBST();
        int[] arr = {1, 3, 4, 6, 7, 8, 9};
        Node root = md.constructTree(arr, 0);
        int total = md.count(root);
        List<Integer> medianPlaces = new ArrayList<>();
        if (total % 2 == 0) {
            medianPlaces.add(total / 2);
        }
        medianPlaces.add((total + 1) / 2);
        List<Integer> medians = new ArrayList<>();
        md.median(root,medianPlaces,medians,0);

        for (Integer median: medians){
            System.out.println(median);
    }

}



}
