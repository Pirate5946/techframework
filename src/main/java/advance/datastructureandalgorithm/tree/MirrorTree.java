package advance.datastructureandalgorithm.tree;

import java.util.Stack;

/**
 * description: 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * @author : LIUTAO
 * create at : 2020/4/17 下午11:08
 **/
public class MirrorTree {


    public void mirrorRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirrorRecursion(root.left);
        mirrorRecursion(root.right);
    }

    public void mirrorNonRecursion(TreeNode root) {
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left != null||node.right != null){
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }
        }
    }
}
