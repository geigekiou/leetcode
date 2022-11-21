package leetcode;

import javax.print.DocFlavor;
import java.util.*;
import java.util.function.BinaryOperator;

public class prac {
    public static int findRepeatNumber(int[] nums) {
        int[] hash = new int[nums.length-1];
        for (int i = 0; i < nums.length; i++) {
            if(hash[nums[i]] > 0) return nums[i];
            else hash[nums[i]]++;
        }
        return 0;
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0) return false;
        int i=0,j=matrix[0].length-1;
        while (i < matrix.length && j >= 0){
            if(matrix[i][j] == target) return true;
            else if(matrix[i][j] > target){
                j--;
            }
            else if(matrix[i][j] < target){
                i++;
            }
        }
        return  false;

//        while(i<matrix.length && j<matrix[0].length && matrix[i++][j++]<target);
//        if(i<matrix.length && j<matrix[0].length)
//
//
//        if(matrix[i][j] > target) return false;
//        else if(matrix[i][j] == target) return true;
//        else if(matrix[i][j] < target){
//            return findNumberIn2DArraySup(matrix,target,i+1,j) || findNumberIn2DArraySup(matrix,target,i,j+1);
//        }
//        return false;
    }

    public static String replaceSpace(String s) {
//        输入：s = "We are happy."
//        输出："We%20are%20happy."
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==' '){
                sb.append("%20");
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String reverseStr(String s, int k) {
        StringBuilder sb2 = new StringBuilder();

        int i = 0, j = 0;
        //s = "abcdefg", k = 2
        for (int l = 0; l < s.length(); l+=2*k) {
            StringBuilder temp = new StringBuilder();
            i = l+k>s.length()?s.length():l+k;
            j = l+2*k>s.length()?s.length():l+2*k;
            temp.append(s.substring(l, i)).reverse();
            sb2.append(temp);
            if(i<j)
                sb2.append(s.substring(i,j));
        }
        System.out.println(sb2.toString());
        return sb2.toString();
    }

    public static int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int i = 0;
        while (head!=null){
            stack.push(head.val);
            i++;
            head = head.next;
        }
        int[] res = new int[i];
        i = 0;
        while (!stack.isEmpty()){
            res[i] = stack.pop();
            i++;
        }
        return res;
    }

    public static int fib(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        if(dp[n] > 1000000007){
            dp[n] %= 1000000007;
        }
        return dp[n];
    }

    public int numWays(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;
        int[] dp = new int[n+1];
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public int minArray(int[] numbers) {
        return Arrays.stream(numbers).min().getAsInt();
    }

    public static boolean existBeyond(char[][] board, int x, int y){
        if( x< board.length && x>=0 && y< board[0].length && y>=0) return true;
        else return false;
    }

    public static boolean existSup(char[][] board, String word, int x, int y, int index, boolean[][] traverse){
        if(traverse[x][y]) return false;
        if(word.charAt(index) == board[x][y]){
            traverse[x][y] = true;
            if(index + 1 == word.length())
                return true;
            else{
                if(existBeyond(board,x-1,y) && existSup(board,word,x-1,y,index+1, traverse))  return true;
                if(existBeyond(board,x+1,y) && existSup(board,word,x+1,y,index+1, traverse))  return true;
                if(existBeyond(board,x,y-1) && existSup(board,word,x,y-1,index+1, traverse))  return true;
                if(existBeyond(board,x,y+1) && existSup(board,word,x,y+1,index+1, traverse))  return true;
            }
            traverse[x][y] = false;
        }
        else return false;
        return false;
    }

    public static boolean exist(char[][] board, String word) {
        if(board.length==0 || board[0].length==0) return false;
        char firstWord = word.charAt(0);
        int rowlen = board.length;
        int columnlen = board[0].length;
        boolean[][] traverse = new boolean[rowlen][columnlen];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == firstWord){
                    if(existSup(board,word,i,j,0,traverse)) return true;
                }
            }
        }
        return false;
    }

    public static int cuttingRope(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 1;
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        int max = 1;
        //dp代表的是数值为j时的最大乘积；
        // 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
        for (int i = 3; i < n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(dp[j]*(i-j),(i-j)*j));
                if(dp[i] > 1000000007) dp[i] = dp[i]%1000000007;
            }
        }
        return dp[n];
    }

    public static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    public static double mypowSup(double x, int n){
        if(n == 1) return x;
        if(n%2==0) return mypowSup(x*x,n>>1);
        else return mypowSup(x*x,n>>1)*x;
    }

    public static double myPow(double x, int n) {
        if (x == 0) return 0;
        if (x == 1) return 1;

        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1/x;

        double half = myPow(x,n<<1);
        double mod = myPow(x,n%1);
        return half*half*mod;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode start = head;

        if(head.val==val) return head.next;
        ListNode pre = head;
        head = head.next;
        while (head.val!=val){
            pre = head;
            head = head.next;
        }
        pre.next = head.next;

        return start;
    }

    public int[] exchange(int[] nums) {
//        int[] res = new int[nums.length];
//        int i = 0, j = nums.length-1;
//        for (int m = 0; m < res.length; m++) {
//            if(nums[m]%2==0) {
//                res[j] = nums[m];
//                j--;
//            }
//            else {
//                res[i] = nums[m];
//                i++;
//            }
//        }
//        return res;
        int left = 0, right = nums.length-1;
        int temp;
        while (left<right){
            if(nums[left]%2==1){
                left ++;
            }
            else{
                if(nums[right]%2==0){
                    right--;
                }
                else if(nums[right]%2==1){
                    temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
            }
        }
        return nums;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 1; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next==null) return head; //
        ListNode after = reverseList(head.next);
        ListNode start = after;
        while (after!=null) {
            after = after.next;
        }
        after = head;
        head.next = null;
        return start;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode head = l3;
        int v1, v2;
        while(l1!=null || l2!=null){
            if(l1==null) v1 = Integer.MAX_VALUE;
            else v1 = l1.val;

            if(l2==null) v2 = Integer.MAX_VALUE;
            else v2 = l2.val;

            if(v1<v2){
                l3.next = new ListNode(v1);
                l1 = l1.next;
            }

            else {
                l3.next = new ListNode(v2);
                l2 = l2.next;
            }

            l3 = l3.next;
        }
        return head.next;
    }

    public static int[] spiralOrder(int[][] matrix) {
        int row = matrix.length;
        if(row == 0) return new int[0];
        int column = matrix[0].length;
        int[] res = new int[row*column];
        if(column ==0 ) return res;

        List<Integer> list = new ArrayList<>();
        int right = matrix[0].length-1;//5
        int bottom = matrix.length-1;//4
        int left = 0;
        int up = 0;
        int turns = 0;
        while (right>left && up<bottom){
            for (int i = left; i < right; i++) {
                list.add(matrix[up][i]);
            }
            for (int i = up; i < bottom; i++) {
                list.add(matrix[i][right]);
            }
            for (int i = right; i > left; i--) {
                list.add(matrix[bottom][i]);
            }
            for (int i = bottom; i > up; i--) {
                list.add(matrix[i][left]);
            }
            turns++;
            right--;
            left++;
            up++;
            bottom--;

        }
        if(right==left){
            for (int i = turns; i < (matrix.length - turns); i++) {
                list.add(matrix[i][right]);
            }
        }
        else if(up==bottom){
            for (int i = turns; i < (matrix[0].length - turns); i++) {
                list.add(matrix[bottom][i]);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {//pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek()==popped[j] && j< popped.length){
                stack.pop();
                j++;
            }
        }

        if(j==popped.length) return true;
        else return false;
    }

    public static boolean verifyPostorder(int[] postorder) {
        if(postorder.length <= 1) return true;
        int root_value = postorder[postorder.length-1];
        int point = postorder.length-1;
        for (; point >= 0; point--) {
            if(postorder[point] < root_value){
                break;
            }
        }

        for (int i = 0; i <= point; i++) {
            if(postorder[i] < root_value) continue;
            else return false;
        }

        for (int i = point+1; i < postorder.length-1; i++) {
            if(postorder[i] > root_value) continue;
            else return false;
        }

        return verifyPostorder(Arrays.copyOfRange(postorder,0,point+1))&&
                verifyPostorder(Arrays.copyOfRange(postorder,point+1,postorder.length-1));
    }

    public static void permutationSup(List<String> reslist,  String s, StringBuilder sb,
                                       int depth,  int begin, boolean[] log){

        if(sb.length()>depth) return;
        if(sb.length() == depth){
            reslist.add(sb.toString());
            return;
        }

        for (int i = 0; i < depth; i++) {
            if(i>0 && s.charAt(i) == s.charAt(i - 1) && log[i-1]==false) continue;
            if(!log[i]){
                sb.append(s.charAt(i));
                log[i]=true;
                permutationSup(reslist,s,sb,depth,i+1,log);
                log[i] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    public static String[] permutation(String s) {
        if (s.length()==0) return null;

        int begin = 0;
        int depth = s.length();
        List<String> reslist = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean[] log = new boolean[depth];
        Arrays.fill(log,false);

        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String s1 = new String();
        for (int i = 0; i < chars.length; i++) {
            s1 += chars[i];
        }

        permutationSup(reslist,s1,sb,depth,0,log);
        String[] resArr = new String[reslist.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = reslist.get(i);
            System.out.println(resArr[i]);
        }
        return resArr;
}

    public static int majorityElement(int[] nums) {// 1 2 2 3
        int res = nums[0];
        int count = 1;
        if (nums.length==1) return res;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == res){
                count++;
            }

            if(count == 0){
                res = nums[i];
                count = 1;
            }
            count--;
        }
        return res;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
//arr = [3,2,1], k = 2
//        List<Integer> monotonicStack = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) {
//            if(monotonicStack.size()<k){
//                monotonicStack.add(arr[i]);
//            }
//            else {
//
//            }
//        }
        int[] res = new int[k];
        if(k==0) return res;
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> (i2 - i1));
        for (int i = 0; i < arr.length; i++) {
            if(pq.size()<k){
                pq.offer(arr[i]);
            }
            else {
                if(arr[i]<pq.peek()){
                    pq.poll();
                    pq.offer(arr[i]);
                }
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    public static int maxSubArray(int[] nums) {
        //nums = [-2,1,-3,4,-1,2,1,-5,4]
        int res = 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i] , nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public char firstUniqChar(String s) {
        //"leetcode"
        int[] hashtable = new int[26];
        Arrays.fill(hashtable,0);
        for (int i = 0; i < s.length(); i++) {
            hashtable[(int)(s.charAt(i)-'a')]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if(hashtable[s.charAt(i)-'a']==1) return s.charAt(i);
        }
//        for (int i = 0; i < hashtable.length; i++) {
//            if(hashtable[i]==1) return (char) ('a'+hashtable[i]);
//        }
        return ' ';
    }

    public static int nthUglyNumber(int n) {
        //1 2 3 4 5 6
        int res = 1;
        if(n==1) return 1;
        int[] dp = new int[n];

        int i = 0,j = 0,k = 0;
        dp[0] = 1;
        for (int l = 1; l < n; l++) {
            int tmp =  Math.min(dp[i]*2,Math.min(dp[j]*3,dp[k]*5));
            //三个链表可能有相同元素，所以只要是最小的，都要移动指针
            if(tmp == dp[i]*2)i++;
            if(tmp == dp[j]*3)j++;
            if(tmp == dp[k]*5)k++;
            dp[l] = tmp;
        }
        return dp[n-1];
    }

    public static int lengthOfLongestSubstring(String s) {
        //"abcabcbb"
        if(s.length()==0) return 0;
        int max = 0;
        int start = 0, end = 0;
        Deque<Character> win = new ArrayDeque<>();

        for (int i = 0; end < s.length() && i < s.length(); i++) {
            if(!win.contains(s.charAt(i))){
                win.offer(s.charAt(i));
            }
            else {
                while (win.peek()!=s.charAt(i)){
                    win.poll();
                }
                win.poll();
                win.offer(s.charAt(i));
            }
            max = Math.max(max,win.size());
        }
        return max;
    }

    public static int maxValue(int[][] grid) {

        int row = grid.length;
        int column = grid[0].length;
        int[][] dp = new int[row][column];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }

        for (int j = 1; j < column; j++) {
            dp[0][j] = dp[0][j-1]+grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j])+grid[i][j];
            }
        }
        return dp[row-1][column-1];
    }

    public static int translateNumLegal(String value){
        int num = Integer.valueOf(value);
        if(10 <= num && num <= 26){
            return 1;
        }
        else return 0;
    }

    public static int translateNum(int num) {
        String value = String.valueOf(num);
        //  1 2 2 5 8
        int[] dp = new int[value.length()+1];
        dp[0] = 1;
        if(value.length()==1){
            return 1;
        }
        dp[1] = 1;
        // 1 2 2 5 8
        for (int i = 2; i < dp.length; i++) {
            if(translateNumLegal(value.substring(i-2,i))==1)
                dp[i] = dp[i-1]+dp[i-2];
            else {
                dp[i] = dp[i-1];
            }
        }
        return dp[dp.length-1];
    }

    public static String minNumber(int[] nums) {
        //[3,30,34,5,9]
        //1,1,1
        //12 121   12121
        //121_12
        PriorityQueue<String> pq= new PriorityQueue<>((str1, str2) ->
        {
            if(str1.length()==str2.length()){
                int i=0;
                while (i < str1.length() && str1.charAt(i)-str2.charAt(i)==0){
                    i++;
                }
                i=(i==str1.length())?i-1:i;
                return str1.charAt(i)-str2.charAt(i);
            }
            else {
                int j = 0;
                int ch1,ch2;
                while (j<str1.length() || j<str2.length()){
                    ch1 = j<str1.length()?str1.charAt(j):str1.charAt(0);
                    ch2 = j<str2.length()?str2.charAt(j):str2.charAt(0);
                    if(ch1-ch2==0) j++;
                    else {
                        return ch1-ch2;
                    }
                }
            }
            return str1.length()-str2.length(); //12 121
        });

        for (int i = 0; i < nums.length; i++) {
            pq.offer(String.valueOf(nums[i]));
        }

        String res = "";

        while (!pq.isEmpty()){
            res += pq.poll();
        }

        return res;
    }

    public int findNthDigit(int n) {
        // 0-9【10】 10-99【90】100-999【900】1000-9999【9000】
        int res = 0;
        int i = 0;
        return res;
    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode ap = headA;
        ListNode bp = headB;
        while(ap!=bp){
            ap = (ap==null)?headB:ap.next;
            bp = (bp==null)?headA:bp.next;
        }
        return ap;
    }

    public static int search(int[] nums, int target) {
        int high = nums.length-1;
        int low = 0;
        int index = 0;
        int mid = (high+low)/2;
        boolean flag = false;
        int res = 0;

        while (high>=low){
            if(nums[mid]>target){
                high = mid-1;
                mid = (high+low)/2;
            }
            else if (nums[mid]<target){
                low = mid+1;
                mid = (high+low)/2;
            }
            else {
                index = mid;
                flag = true;
                res++;
                break;
            }
        }

        if(!flag) return 0;

        for (int i = index+1; i < nums.length-1; i++) {
            if(nums[i] == nums[index]) res++;
        }

        for (int i = index-1; i >= 0; i--) {
            if(nums[i] == nums[index]) res++;
        }

        return res;
    }

    public static int missingNumber(int[] nums) {
//        int n = nums.length;
//        int sum = (1+n)*n/2;
//        for (int i = 0; i < nums.length; i++) {
//            sum -= nums[i];
//        }
//        return sum;
        int high = nums.length-1;
        int low = 0;
        while (low<=high){
            int mid = (high+low)/2;
            if(nums[mid] == mid){
                low = mid+1;
            }
            else if(nums[mid] > mid){
                high = mid-1;
            }
        }
        return low;

//        输入: [0,1,3]
//        输出: 2

//        输入: [0,1,2,3,4,5,6,7,9]
        //[0,2,3,4,5,6,7,8,9]
//        输出: 8
    }

    public static int[] twoSum(int[] nums, int target) {
//        输入：nums = [10,26,30,31,47,60], target = 40
//        输出：[10,30] 或者 [30,10]
        for (int i = 0; i < nums.length/2; i++) {
            int search = Arrays.binarySearch(nums,target-nums[i]);
            if(search>=0 && search!=i)
                return new int[]{nums[i],target-nums[i]};
        }
        return null;
    }

    public static int[] singleNumbers(int[] nums) {
//输入：nums = [4,1,4,6] O(n) O(1)
        int xor_output = 0;
        for (int i = 0; i < nums.length; i++) {
            xor_output ^= nums[i];
        }//10010
        int flag = xor_output ^ (-xor_output);
        int resnum = 0;
        for (int val : nums) {
            if ((flag & val) != 0) {
                resnum ^= val;
            }
        }
        return new int[]{resnum, xor_output^resnum};
    }

    public static int singleNumber(int[] nums) {
        //自己and自己：自己---> 9 and 7 and 2
        // xor ---> 9 xor 7 xor 2
        int[] binary = new int[32];
        for (int val : nums) {
            for(int j = 0 ; j <32;j++){
                binary[j] += (val>>j & 1) == 1 ? 1 : 0;
            }
        }

        int res = 0;
        for (int i = 31; i >= 0; i--) {
            if(binary[i]%3==0){
                res|=1;
            }
            res = res<<1;
        }
        return res;
    }

    public static int[][] findContinuousSequence(int target) {
//        输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
//        序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
//
//        示例 1：
//
//        输入：target = 9
//        输出：[[2,3,4],[4,5]]
        // 10 ---
        // 1 2 3 4
        // 0 1 2  3 4 5
        List<int[]> list = new ArrayList<>();
        int i=0,j=1;
        int sum =1;
        while(i <= target/2 && i<j){
            if(sum<target){
                j++;
                sum+=j;
            }
            else if(sum>target){
                sum-=i;
                i++;
            }
            else {
                if(i==0) i++;
                int[] targetpart = new int[j-i+1];
                for (int k = 0; k < j-i+1 ; k++) {
                    targetpart[k] = i+k;
                }
                list.add(targetpart);
                sum-=i;
                i++;
            }
        }
        int[][] res = new int[list.size()][];
        for (int m = 0; m < list.size(); m++) {
            res[m] = list.get(m);
        }

        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
        return res;
    }

    public static String reverseWords(String s) {
        s = s.trim();
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strings.length-1; i >= 0; i--) {
            if(strings[i]!=null
                    &&
            !strings[i].equals(" "))
                sb.append(strings[i]+" ");
        }
        return sb.toString().trim();
    }

    public static String reverseLeftWords(String s, int n) {
//        输入: s = "lrlose umgh", k = 6
//        输出: "umghlrlose"
        return s.substring(n)+s.substring(0,n);
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
//        输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//        输出: [3,3,5,5,6,7]
//        解释:
//
//        滑动窗口的位置                最大值
//        ---------------               -----
//       [1  3  -1] -3  5  3  2  1       3
//        1 [3  -1  -3] 5  3  2  1       3
//        1  3 [-1  -3  5] 3  2  1       5
//        1  3  -1 [-3  5  3] 2  1       5
//        1  3  -1  -3 [5  3  2] 1       5
//        1  3  -1  -3  5 [3  2  1]      3
        //1,3,-1,-3,5,3,2,1
// 维持一个单调栈？ 单调递减栈
        if(nums.length==1) return new int[]{nums[0]};
        int[] resarr = new int[nums.length-k+1];
        int j = 0;
        Deque<Integer> win = new ArrayDeque();
        for (int i = 0; i < nums.length; i++) {
            if(win.size()==0) {
                win.push(i);
            }

            else {
                if (win.peek() == i - k) {
                    win.poll();
                }

                if (win.isEmpty() ||
                        nums[win.peekLast()] > nums[i]) {
                    win.offer(i);
                } else if (nums[win.peekLast()] <= nums[i]) {
                    while (!win.isEmpty() &&
                            nums[win.peekLast()] <= nums[i]) {
                        win.removeLast();
                    }
                    win.offer(i);
                }
            }
            if(i>=k-1){
                resarr[j] = nums[win.peekFirst()];
                j++;
            }
            }
//        win.offer(nums[0]);
//        int index = 1;
//        while (win.peek()< nums.length && win.size()<k){
//            win.offer(nums[index]);
//        }
        System.out.println(Arrays.toString(resarr));
        return resarr;
    }
}

class CQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public CQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void appendTail(int value) {
        stackIn.push(value);
    }

    public void dump(){
        while (!stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }
    }

    public int deleteHead() {
        dump();
        if  (stackOut.isEmpty()) return -1;
        else return stackOut.pop();
    }


}

class MinStack {
    public Deque<Integer> stack;
    public Deque<Integer> stackSub;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<>();
        stackSub = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        int minValue = Integer.MIN_VALUE;
        int temp;
        while (!stack.isEmpty()){
            temp = stack.pop();
            if(temp < minValue){
                minValue = temp;
            }
            stackSub.push(temp);
        }
        while (!stackSub.isEmpty()){
            stack.push(stackSub.pop());
        }
        return minValue;
    }
//               5
//             /  \
//             2   6
//            / \   \
//           1   3    7
//          [1,3,2,7,6,5]
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public Node copyRandomList(Node head) {
        if(head==null) return null;

        for (Node iterator = head; iterator!=null; iterator = iterator.next.next) {
            Node copyNode = new Node(iterator.val);
            copyNode.next = iterator.next;
            iterator.next = copyNode;
        }

        for (Node randomitor = head; randomitor!=null; randomitor = randomitor.next.next) {
            if(randomitor.random!=null)
                randomitor.next.random = randomitor.random.next;
        }

        Node newnode = head.next;
//        for (Node partIterator = newnode; partIterator!=null; partIterator = partIterator.next) {
//            if (partIterator.next!=null)
//                partIterator.next = partIterator.next.next;
//            }

        for (Node node = head, temp = null; node != null && node.next != null;) {
            temp = node.next;
            node.next = temp.next;
            node = temp;
        }


        return newnode;
    }
}

class Node2 {
    public int val;
    public Node2 left;
    public Node2 right;

    Node pre = null ;
    Node head = null;

    public Node2() {}

    public Node2(int _val) {
        val = _val;
    }

    public Node2(int _val,Node2 _left,Node2 _right) {
        val = _val;
        left = _left;
        right = _right;
    }

    public Node2 treeToDoublyList(Node2 root) {
        List<Node2> node2List = new ArrayList<>();
        treeToDoublyListSup(root, node2List);
        int min = node2List.get(0).val;
        int minindex = 0;


        for (int i = 0; i < node2List.size(); i++) {
            int rightindex = i+1 <= node2List.size()-1?i+1:0;
            node2List.get(i).right = node2List.get(rightindex);

            int leftindex = i-1 >= 0?i-1:node2List.size()-1;
            node2List.get(i).left = node2List.get(leftindex);

            if(node2List.get(i).val<min){
                min = node2List.get(i).val;
                minindex = i;
            }
        }

        return node2List.get(minindex);
    }

    public void treeToDoublyListSup(Node2 root, List<Node2> node2List){
        if(root == null) return;
        treeToDoublyListSup(root.left,node2List);
        node2List.add(root);
        treeToDoublyListSup(root.right,node2List);
    }



};

class MedianFinder {
    PriorityQueue<Integer> pq1;
    PriorityQueue<Integer> pq2;

    public int N;

    /** initialize your data structure here. */
    public MedianFinder() {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>((i1,i2)->(i2-i1)); //左边大顶堆
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((i1,i2)->(i1-i2));// 右边小顶堆
        N = 0;
    }

    public void addNum(int num) { //       1 3 4 6 3
        if(N==0) {
            pq1.offer(num);
            return;
        }
        if(N%2==1){//目前是奇数个，插入这个成为偶数, 插入的放在右边，两边一样大小
            pq2.offer(num);
            if (pq1.peek() > pq2.peek()){
                pq1.offer(pq2.poll());
                pq2.offer(pq1.poll());
            }
        }
        else if (N % 2 == 0) {//目前是偶数，两边一样长，插入后放左边
            pq1.offer(num);
            if (pq1.peek() > pq2.peek()){
                pq1.offer(pq2.poll());
                pq2.offer(pq1.poll());
            }
        }
        N++;

    }

    public double findMedian() {
        double res = 0;
        if (pq1.size()!=pq2.size()){
            res = pq1.peek();
        }
        else {
            res = (double) (pq1.peek()+ pq2.peek())/2;
        }
        return res;
    }
}


