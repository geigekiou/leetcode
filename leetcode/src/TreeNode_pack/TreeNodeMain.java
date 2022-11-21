package TreeNode_pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeNodeMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //ArrayList<String> treeinput = new ArrayList<>();
        //String[] treeinput = new String[];
        String str = new String();
        System.out.println("please input your treenode array:!");
        String[] treeinput = scanner.nextLine().split(",");
        TreeNode root = TreeNodeSolution.arrayToTreeNode(treeinput);
        //TreeNodeSolution.rob3(root);
        int num = 1;
//        TreeNodeSolution.pathSum(root,num);
        TreeNodeSolution.kthLargest(root,1);

//        List<String>path = new ArrayList<String>();
//        TreeNodeSolution.binaryTreePathsHelper(root,"",path);
//
//        int[] nums = new int[treeinput.length];
//        for (int i = 0; i < treeinput.length; i++) {
//            nums[i] = Integer.valueOf(treeinput[i]);
//        }
//        TreeNode root1 = TreeNodeSolution.constructMaximumBinaryTree(nums);
//        TreeNodeSolution.postOrderTraverse(root1);

//        System.out.println("please input your another treenode array:!");
//        treeinput = scanner.nextLine().split(",");
//        TreeNode root2 = TreeNodeSolution.arrayToTreeNode(treeinput);
        //TreeNodeSolution.preOrderTraverse(root1);
        //TreeNodeSolution.preOrderTraverse(root2);
        //System.out.println(TreeNodeSolution.maxDepth(root1));
        //System.out.println(TreeNodeSolution.maxDepth(root2));
        //TreeNodeSolution.preOrderTraverse(TreeNodeSolution.invertTree(root1));
        //System.out.println(TreeNodeSolution.isSymmetric(root));
    }
}
