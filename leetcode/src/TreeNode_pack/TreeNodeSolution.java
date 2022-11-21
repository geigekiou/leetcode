package TreeNode_pack;
import com.sun.source.tree.Tree;

import java.nio.file.Path;
import java.util.*;

public class TreeNodeSolution {
    public static TreeNode arrayToTreeNode(String[] array){
        if(array.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(array[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeft = true;
//队列里面永远是这一层的节点，因为每个节点肯定对应俩个子代【这是数组决定的；】
        for(int i = 1; i < array.length; i++){
            TreeNode node = queue.peek();
            if(isLeft){
                if(!array[i].equals("null")){
                    node.left = new TreeNode(Integer.valueOf(array[i]));
                    queue.offer(node.left); // 入队
                }
                isLeft = false;
            }
            else {
                if(!array[i].equals("null")){
                    node.right = new TreeNode(Integer.valueOf(array[i]));
                    queue.offer(node.right);
                }
                queue.poll(); //出队
                isLeft = true;
            }
        }
        return root;
    }

    public static void preOrderTraverse(TreeNode root){
        if(root==null) return;
        System.out.println(root.val);
        if(root.left!=null) preOrderTraverse(root.left);
        if(root.right!=null) preOrderTraverse(root.right);
        return ;
    }

    public static void postOrderTraverse(TreeNode root){
        if(root==null) return;
        if(root.left!=null) postOrderTraverse(root.left);
        if(root.right!=null) postOrderTraverse(root.right);
        System.out.println(root.val);
        return ;
    }

    public static int maxDepth(TreeNode root) {
        //终止条件：当树为空时结束递归，并返回当前深度0
        if(root == null){
            return 0;
        }
        //root的左、右子树的最大深度
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        //返回的是左右子树的最大深度+1
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static boolean SymmetricHelper(TreeNode rl, TreeNode rr){
        if(rl == null && rr == null) return true;
        else if( (rl != null && rr == null)||(rr != null && rl == null)) return false;

        boolean flag = true;
        if(rl.val == rr.val) {
            flag = true;
            flag = flag && SymmetricHelper(rl.left, rr.right) && SymmetricHelper(rl.right, rr.left);
        }
        else flag = false;

        return flag;
    }

    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return SymmetricHelper(root.left,root.right);
    }

    public static boolean isBalanced(TreeNode root) {
//        给定一个二叉树，判断它是否是高度平衡的二叉树。
//        本题中，一棵高度平衡二叉树定义为：
//        一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
        if(root == null) return true;
        boolean flag1 = Math.abs(maxDepth(root.left)-maxDepth(root.right)) <=1;
        boolean flag2 = isBalanced(root.left)&&isBalanced(root.right);
        if(flag1&&flag2) return true;
        else return false;
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2){
        if (root1==null && root2==null) return null;
        int rootval = 0;
        if (root1!=null && root2==null) {
            rootval +=root1.val;
            return new TreeNode(rootval,mergeTrees(root1.left,null),mergeTrees(root1.right,null));
        }

        else if (root2!=null && root1==null){
            rootval +=root2.val;
            return new TreeNode(rootval,mergeTrees(null,root2.left),mergeTrees(null,root2.right));
        }

        else {
            rootval += root1.val+root2.val;
            return  new TreeNode(rootval,mergeTrees(root1.left,root2.left),mergeTrees(root1.right,root2.right));
        }
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        if(root.left != null || root.right!= null)
        {
        TreeNode templeft = root.left;
        TreeNode tempright = root.right;
        root.right =  invertTree(templeft);
        root.left =  invertTree(tempright);
        }
        return root;
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length == 0) return null;

        int numsmax = -1;
        int maxindex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > numsmax) {
                numsmax = nums[i];
                maxindex = i;
            }
        }
        
        TreeNode root = new TreeNode(numsmax);
        int[] leftarr = Arrays.copyOfRange(nums,0, maxindex);  //左闭右开
        int[] rightarr = Arrays.copyOfRange(nums,(maxindex+1),nums.length);
        root.left = constructMaximumBinaryTree(leftarr);
        root.right = constructMaximumBinaryTree(rightarr);
        return root;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        return (p.val==q.val)&&isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

//    public boolean hasPathSumHelper(TreeNode root, int targetSum){
//        boolean flagl = false, flagr = false;
//        targetSum = targetSum- root.val;
//        if(targetSum==0 && (root.left==null || root.right==null)) return true;
//        if(targetSum< 0) return false;
//
//        if(root.left != null) flagl = hasPathSumHelper(root.left, targetSum);
//        if(root.right != null) flagr = hasPathSumHelper(root.right, targetSum);
//
//        return flagl || flagr;
//    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null) return false;

        if(root.right == null && root.left==null){ //是叶子吗
            if(root.val == targetSum) return true;
            else return false;
        }
        else{ //不是叶子
            return hasPathSum(root.left, targetSum)||hasPathSum(root.right, targetSum);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if(p.val==root.val || q.val==root.val) return root;
        int max = Math.max(p.val,q.val);
        int min = Math.min(p.val,q.val);
        TreeNode iterator = root;

        if(iterator.val>max) iterator = iterator.left;
        else if(iterator.val<min) iterator = iterator.right;
        else {
            return iterator;
        }
        return iterator;
    }

    public void widthOrderTraverse(TreeNode root){
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val+"  ");
            if (node.left != null ) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public void depthOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val+"  ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static List<String> binaryTreePathsHelper(TreeNode root, String pathstr, List<String> path){
        if(root.left==null && root.right==null) {
            path.add(pathstr+root.val);
            return path;
        }
        pathstr = pathstr+root.val+"->";
        if(root.left!=null)
            binaryTreePathsHelper(root.left,pathstr, path);
        if(root.right!=null)
            binaryTreePathsHelper(root.right,pathstr, path);
        return path;
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<String>();
        }
        List<String>path = new ArrayList<String>();
//
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val+"  ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if(node.right == null&& node.left==null){

            }
        }
        return path;
    }

    public static int[] rob(TreeNode root){//作用；返回当前结点选或者不选的最大值，
        int[] dp = new int[2];
        if(root.right==null||root.left==null) {
            dp[0] = root.val;
            if(root.right!=null){
                dp[1] += root.right.val;
            }
            if(root.left!=null){
                dp[1] += root.left.val;
            }
            return dp;
        }
//  结束条件：出现叶子结点；
        // 本轮做什么？有结点和函数, 选的话，左右不能选
        int[] dpl = rob(root.left);
        int[] dpr = rob(root.right);
        dp[0] = dpl[1]+ dpr[1];
        //不选的话
        dp[1] = Math.max(dpl[0],dpl[1])+Math.max(dpr[0],dpr[1]);
        return dp;
    }

    public static int rob3(TreeNode root) {
        int[] dp = rob(root);
        return Math.max(dp[0],dp[1]);
    }

//    public static TreeNode buildTreeSup(int[] preorder, int[] inorder){
//
//    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) { //  根左右。。左 根 右

        if(preorder.length == 0 || inorder.length==0) return null;
        TreeNode head = new TreeNode(preorder[0]);

        int k = 0;
        int m = 0;
        while(inorder[k] != preorder[0]){
            k++;
        }

        int[] inorderleftPart = Arrays.copyOfRange(inorder,0,k);
        int[] inorderrightPart = Arrays.copyOfRange(inorder,k+1,inorder.length);

        if(inorderleftPart.length<=0){//不存在左子树了
            head.left = null;
        }
        else {
            int[] preorderleftPart = Arrays.copyOfRange(preorder, 1,1+k);
            head.left = buildTree(preorderleftPart, inorderleftPart);
        }

        int[] preorderrightPart = Arrays.copyOfRange(preorder, preorder.length - inorderrightPart.length,preorder.length);
        head.right = buildTree(preorderrightPart, inorderrightPart);
        return head;
    }

    public boolean isSubStructureSup(TreeNode A, TreeNode B){
        if(B==null) return true;
        if(A==null) return false;
        if(A.val== B.val) return isSubStructureSup(A.left,B.left) && isSubStructureSup(A.right,B.right);
        else return false;
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null || B==null) return true;
        return isSubStructureSup(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }

    public TreeNode mirrorTree(TreeNode root) {
        if(root==null) return root;

        TreeNode temp;
        temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }

    public boolean isSymmetric2Sup(TreeNode root1, TreeNode root2){
        if(root1 == null || root2 == null){
            return root1==root2;
        }
        if(root1.val != root2.val) return false;
        else return isSymmetric2Sup(root1.left,root2.right)&&isSymmetric2Sup(root1.right,root2.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        if(root==null) return true;
        return isSymmetric2Sup(root.left,root.right);
    }

    public static int[] levelOrder(TreeNode root) {
        if(root==null) return new int[0];
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            list.add(temp.val);
            if(temp.left!=null) queue.offer(temp.left);
            if(temp.right!=null) queue.offer(temp.right);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sizelist = new ArrayList<>();
        if(root==null) return res;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            sizelist.add(size);
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if(temp.left!=null) queue.offer(temp.left);
                if(temp.right!=null) queue.offer(temp.right);
                list.add(temp.val);
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }

    public static List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sizelist = new ArrayList<>();
        if(root==null) return res;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int turns = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            sizelist.add(size);
            List<Integer> list = new ArrayList<>();
            if(turns%2 == 0)
                for (int i = 0; i < size; i++) {
                    TreeNode temp = queue.poll();
                    if(temp.left!=null) queue.offer(temp.left);
                    if(temp.right!=null) queue.offer(temp.right);
                    list.add(temp.val);
                }
            else{
                //  1 2 3 4
                for (int i = size-1; i >= 0; i--) {
                    TreeNode temp = queue.poll();
                    if(temp.left!=null) queue.offer(temp.left);
                    if(temp.right!=null) queue.offer(temp.right);
                    list.add(temp.val);
                }
                Collections.reverse(list);
            }
            res.add(new ArrayList<>(list));
            turns++;
        }
        return res;
    }

    public static void pathSumSup(TreeNode root, int target,
                                  List<List<Integer>> res,
                                  List<Integer> path, int pathsum){
        if (root==null) return;
        pathsum += root.val;
        path.add(root.val);
        if(target==pathsum && root.right==null && root.left==null){
            res.add(new ArrayList(path));
        }
        else {
            pathSumSup(root.left,target,res,path,pathsum);
            pathSumSup(root.right,target,res,path,pathsum);
        }
        path.remove(path.size()-1);
        pathsum -= root.val;
    }

    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        if(root==null){
            return resList;
        }
        List<Integer> path = new ArrayList<>();
        pathSumSup(root,target,resList,path,0);
        return resList;
    }

    public static void myOrderTraverse(TreeNode root, List<Integer> path){
        if(root==null) return;
        myOrderTraverse(root.right,path);
        path.add(root.val);
        myOrderTraverse(root.left,path);
    }

    public static int kthLargest(TreeNode root, int k) {
        List<Integer> path = new ArrayList<Integer>();
        myOrderTraverse(root,path);
        return path.get(path.size()-k);
    }

    public static int maxDepth2(TreeNode root) {
        if(root == null) return 0;
        return 1+Math.max(maxDepth2(root.left),maxDepth2(root.right));
    }


    public static boolean isBalanced2(TreeNode root) {//任意节点的左右子树的深度相差不超过1
        if(root == null) return true;
        if(Math.abs(maxDepth2(root.left) - maxDepth2(root.right)) <= 1){
            return isBalanced2(root.left)&&isBalanced2(root.right);
        }
        else return false;
    }
}

