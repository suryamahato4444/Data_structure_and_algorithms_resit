//QUESTION-2A
package lang;

public class MergeBST {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static TreeNode mergeTrees(int[][] tree) {
        int n = tree.length;
        if (n == 0) {
            return null;
        }
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = buildTree(tree[i]);
        }
        return merge(nodes, 0, n - 1);
    }
    
    private static TreeNode merge(TreeNode[] nodes, int left, int right) {
        if (left == right) {
            return nodes[left];
        }
        int mid = left + (right - left) / 2;
        TreeNode leftTree = merge(nodes, left, mid);
        TreeNode rightTree = merge(nodes, mid + 1, right);
        return mergeTwoTrees(leftTree, rightTree);
    }
    
    private static TreeNode mergeTwoTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.right = mergeTwoTrees(t1.right, t2);
        t2.left = mergeTwoTrees(t1, t2.left);
        return t2;
    }
    
    private static TreeNode buildTree(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            insert(root, arr[i]);
        }
        return root;
    }
    
    private static void insert(TreeNode root, int val) {
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insert(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insert(root.right, val);
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] tree = {{10,8,12},{8,7,-1},{12,11,15}};
        TreeNode result = mergeTrees(tree);
        System.out.println(result.val);
    }
}
