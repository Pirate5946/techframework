package advance.datastructureandalgorithm.tree;

/**
 * description: 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树
 *
 * @author : LIUTAO
 * create at : 2020/4/13 下午2:44
 **/
public class ReConstructBinaryTree {

    /**
     * description:输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树
     */
    public TreeNode preInToTree(int[] pre, int[] in) {
        return reConBTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode reConBTree(int[] pre, int preLeft, int preRight, int[] in, int inLeft, int inRight) {
        //当到达边界条件时候返回null
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        //新建一个TreeNode
        TreeNode root = new TreeNode(pre[preLeft]);
        //对中序数组进行输入边界的遍历
        for (int i = inLeft; i <= inRight; i++) {
            if (pre[preLeft] == in[i]) {
                //重构左子树，注意边界条件
                root.left = reConBTree(pre, preLeft + 1, preLeft + i - inLeft, in, inLeft, i - 1);
                //重构右子树，注意边界条件
                root.right = reConBTree(pre, preLeft + i + 1 - inLeft, preRight, in, i + 1, inRight);
            }
        }
        return root;
    }
}
