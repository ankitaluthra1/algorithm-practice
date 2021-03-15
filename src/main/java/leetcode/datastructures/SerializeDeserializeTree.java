package leetcode.datastructures;


public class SerializeDeserializeTree {

    private TreeNode constructTree(Integer[] input, int index) {
        TreeNode root = null;
        if (index < input.length) {
            TreeNode left = constructTree(input, 2 * index + 1);
            TreeNode right = constructTree(input, 2 * index + 2);
            if (input[index] == null)
                return null;
            root = new TreeNode(input[index], left, right);
        }
        return root;
    }

    int totalNodes = 0;
    String[] serializationArr;

    public String serialize(TreeNode root) {
        serializationArr = new String[100];
        preOrder(root, 0);
        return toArrayString();
    }

    private String toArrayString() {
        StringBuilder sg = new StringBuilder();
        for (int i = 0; i <= totalNodes; i++) {
            String str = serializationArr[i] == null ? "": serializationArr[i];
            if (i == totalNodes)
                sg.append(str);
            else
                sg.append(str).append(",");
        }
        return sg.toString();
    }

    private void preOrder(TreeNode root, int index) {
        if (root == null) {
            addIn(index, "");
            return;
        }
        addIn(index, Integer.toString(root.val));
        preOrder(root.left, (2 * index) + 1);
        preOrder(root.right, (2 * index) + 2);
    }

    private void addIn(int index, String val) {
        if (index >= serializationArr.length) {
            String[] newArray = new String[2 * serializationArr.length];
            System.arraycopy(serializationArr, 0, newArray, 0, serializationArr.length);
            serializationArr = newArray;
        }
        serializationArr[index] = val;
        totalNodes = Math.max(index, totalNodes);
    }

    public TreeNode deserialize(String data) {

        String[] treeStringArray = data.split(",");
        Integer[] arr = new Integer[treeStringArray.length];
        for (int i = 0; i < arr.length; i++) {
            if ("".equals(treeStringArray[i]))
                arr[i] = null;
            else
                arr[i] = Integer.parseInt(treeStringArray[i]);
        }
        return constructTreeFromIntArray(arr, 0);
    }

    private TreeNode constructTreeFromIntArray(Integer[] arr, int index) {
        if (index < arr.length && arr[index] != null) {
            TreeNode node = new TreeNode(arr[index]);
            node.left = constructTreeFromIntArray(arr, (2 * index) + 1);
            node.right = constructTreeFromIntArray(arr, (2 * index) + 2);

            return node;
        }

        return null;
    }

    public static void main(String[] args) {
        SerializeDeserializeTree st = new SerializeDeserializeTree();
        Integer[] input = {4, -7, -3, null, null, -9, -3, 9, -7, -4, null, 6, null, -6, -6, null, null, 0, 6, 5, null, 9, null, null, -1, -4, null, null, null, -2};
        TreeNode root = st.constructTree(input, 0);
        String result = st.serialize(root);
        System.out.println("Serialised Tree: " + result);

        TreeNode tree = st.deserialize(result);
        System.out.println(tree);

    }

}
