package glassdoorQuestions.linkedIn;

import glassdoorQuestions.trasnferwise.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintBottomUpTreeLevel {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null)
            return Collections.emptyList();
        List<List<Integer>> output = new ArrayList<>();
        fillOutput(output, Collections.singletonList(root));
        return output;
    }

    private void fillOutput(List<List<Integer>> output, List<TreeNode> parentList) {
        if (parentList.isEmpty())
            return;
        List<Integer> temp = new ArrayList<>();
        List<TreeNode> nextParentList = new ArrayList<>();
        for (TreeNode parent: parentList){
            temp.add(parent.getValue());
            if (parent.getLeft() != null)
                nextParentList.add(parent.getLeft());
            if (parent.getRight() != null)
                nextParentList.add(parent.getRight());
        }
        output.add(0, temp);
        fillOutput(output, nextParentList);
    }

}
