package algorithm.tree;

/**
 * description:
 *
 * <p>A树是否包含B🌲</p>
 *
 * <p>《程序员代码面试指南》 有解题思路</p>
 *
 * @see <a href="https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tqId=11170&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking">牛客网答题页面</a>
 * @author : LIUTAO
 * create at : 2020/1/5 下午5:55
 **/
public class HasSubTree {
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null) {
            return false;
        }
        return check(root1, root2) || hasSubtree(root1.left, root2) || hasSubtree(root1.right, root2);
    }

    public boolean check(TreeNode h, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (h == null || h.val != t2.val) {
            return false;
        }
        return check(h.left, t2.left) && check(h.right, t2.right);
    }
}
