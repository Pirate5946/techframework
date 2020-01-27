package algorithm.tree;

/**
 * description: 中序遍历树
 *Î
 * @author : LIUTAO
 * create at : 2020/1/21 下午4:00
 **/
public class InOrderRecursive {
    public static void main(String[] args) {
        int[] treeNodeValueArray = {5, 3, 1, 4, 7, 6};

        TreeNode root = TreeUtils.createTree(treeNodeValueArray);

        TreeUtils.inOrder(root);

//        int maxValue = TreeUtils.maxValue(root);
//        System.out.println(maxValue);

        int maxDepth = TreeUtils.maxDepth(root);
        System.out.println("maxDepth：" + maxDepth);
    }

}
