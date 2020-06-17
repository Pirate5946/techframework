package advance.datastructureandalgorithm.tree;

/**
 * description: 整理一些树的工具方法
 *
 * @author : LIUTAO
 * create at : 2020/1/21 下午3:47
 **/
public class TreeUtils {
    public static TreeNode createTree(int[] intArray) {
        TreeNode root = null;
        for (int nodeValue : intArray) {
            root = TreeUtils.insert(root, nodeValue);
        }
        return root;
    }

    private static TreeNode insert(TreeNode root, int nodeValue) {
        if (root == null) {
            return new TreeNode(nodeValue);
        }
        if (nodeValue <= root.val) {
            root.left = insert(root.left, nodeValue);
        }
        if (nodeValue > root.val) {
            root.right = insert(root.right, nodeValue);
        }
        return root;
    }

    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);

    }

    /**
     * description: 树的最大值
     *
     * @author LIUTAO
     * @date 2020/1/25 下午9:06
     * @param root
     * @return int
     */
    public static int maxValue(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int left = maxValue(root.left);
        int right = maxValue(root.right);

        int maxVal = Math.max(left, root.val);
        maxVal = Math.max(right, maxVal);
        return maxVal;
    }

    /**
     * description: 树的最大深度
     *
     * @author LIUTAO
     * @date 2020/1/25 下午9:26
     * @param root
     * @return int
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }





}
