package leetcode;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//这里是链表的方法
public class solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode iterator1 = l1;
            ListNode iterator2 = l2;
            ListNode backNode = new ListNode();
            ListNode iterator3 = backNode;
            int flag = 0;
             // 321+321 = 642 2-4-6
            //实际上是321 1-2-3
            while(iterator1!=null && iterator2!=null){
                iterator3.next = new ListNode((iterator1.val+iterator2.val+flag)%10);
                flag = (iterator1.val+iterator2.val+flag)/10;
                iterator1 = iterator1.next;
                iterator2 = iterator2.next;
                iterator3 = iterator3.next;
            }
            //     9999
//                   99
            //
            if(iterator1!=null || iterator2!=null){
                iterator3.next = (iterator1!=null)?iterator1:iterator2;
                iterator3 = iterator3.next;
                int tempval;
                while(flag!=0){
                    tempval = iterator3.val;
                    iterator3.val = (iterator3.val+flag)%10;
                    flag = (tempval+flag)/10;
                    if(iterator3.next!=null) iterator3 = iterator3.next;
                    else break;
                }
            }

            if(iterator3.next==null && flag!=0){
                iterator3.next = new ListNode(flag);
            }

            return backNode.next;
        }

    public static void traverse(ListNode k){
            while(k!=null){
                System.out.print(k.val);
                k = k.next;
            }
        System.out.println();
        }

    public static ListNode deleteDuplicates(ListNode head){
        Set<Integer> myset = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        while(head!=null){
            myset.add(head.val);
            head = head.next;
        }

        ListNode reshead = new ListNode();
        ListNode res = reshead;
        for (Integer integer : myset) {
            res.next = new ListNode(integer);
            res = res.next;
        }

        return reshead.next;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        nums1 = IntStream.concat(Arrays.stream(nums1).limit(m),Arrays.stream(nums2).limit(n)).sorted().toArray();
        System.out.println(Arrays.toString(nums1));
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode iteratorA = headA;
        ListNode iteratorB = headB;
        while(iteratorA!=null) {lenA++; iteratorA = iteratorA.next;}
        while(iteratorB!=null) {lenB++;iteratorB = iteratorB.next;}
        int gap = Math.abs(lenA - lenB);
        iteratorA = headA;
        iteratorB = headB;
        if(gap==0){
            while(iteratorA!=null && iteratorB!=null){
                if(iteratorA == iteratorB) return iteratorA;
                iteratorA = iteratorA.next;
                iteratorB = iteratorB.next;
            }
        }
        else {
            ListNode iteratorL = (lenA - lenB)>0?headA:headB;
            ListNode iteratorS = (lenA - lenB)>0?headB:headA;
            for (int i = 0; i < gap; i++) {
                iteratorL = iteratorL.next;
            }
            while(iteratorL!=null && iteratorS!=null){
                if(iteratorL == iteratorS) return iteratorL;
                iteratorL = iteratorL.next;
                iteratorS = iteratorS.next;
            }
        }
// 5-6-1-
//         -3-4
//   4-1-
        return null;
    }

    public static int lengthOfLongestSubstring(String s) { //abcabcbb
        int max = 0;
        int front = 0, back = 0;
        Map<Character, Integer> window = new HashMap<Character, Integer>();  //dvdf abba
        while(front < s.length()) {
            if (!window.containsKey(s.charAt(front)) || window.get(s.charAt(front)) < back ){
                window.put(s.charAt(front), front);
            }
            else {
                back = window.get(s.charAt(front))+1;
                window.put(s.charAt(front),front);
            }
            if(max < front - back + 1){max = front - back +1;}
            front++;
        }
        return max;
        //        String opstring = new String();
//        while(len>=1){
//            for (int i = 0; i+len <= s.length(); i++) {
//                opstring = s.substring(i,i+len);
//                int j = 0;
//                for (j = 0; j < len; j++) {
//                    char ch = opstring.charAt(j);
//                    if (opstring.substring(j+1).indexOf(ch)>=0) {
//                        break;
//                    }
//                }
//                if(j==len){ return opstring.length();}
//            }
//            len--;
//        }
//        System.out.println(len);
    }

    public static String longestPalindrome(String s) {
        if(s.length()==1){ return  s;}
        if(s.length()==2){ return  s.charAt(0)==s.charAt(1)?s:""+s.charAt(0);}

        StringBuilder window = new StringBuilder();
        int windowlen = 0;
        int max = 0;
        int left = 0, right=0;
        String maxstring = new String();

        for (int i = 1; i < s.length()-1; i++) {   //b a b a d  首先来一次：自我扩散过程 ccc
            right = i+1;
            left = i-1;
            window.append(s.charAt(i));
            while(right <s.length() && s.charAt(right) == s.charAt(i)){
                window.append(s.charAt(right));
                right++;
            }
            while(left >=0 && s.charAt(left) == s.charAt(i)){
                window.insert(0,s.charAt(left));
                left--;
            }
            //自扩散完毕
            while(right <s.length() && left >=0) { // 下面开始左右扩散
                if (s.charAt(left) == s.charAt(right)) {
                    window.append(s.charAt(right));
                    window.insert(0, s.charAt(left));
                    left-- ;
                    right++;
                } else{
                    break;
                }
            }

            if (window.length()>max){
                maxstring = window.toString();
                max = window.length();
            }
            window.delete(0, window.length());
        }
        return maxstring;
    }

    public static ListNode removeElements(ListNode head, int val) {
        if(head == null)  return head;
        head.next = removeElements(head.next,val);
        if(head.val == val) {
            head = head.next;
        }
        return head;
    }
      //head = [1,2,6,3,4,5,6], val = 6

//    输入：head = [1,2,3,4]
//    输出：[2,1,4,3]
    public static ListNode swapPairs(ListNode head){
//        A整个递归的终止条件。
//        B一级递归需要做什么？  1-2-3-4
//        C应该返回给上一级的返回值是什么？
        if(head==null || head.next == null){
            return head;
        }
        head.next.next = swapPairs(head.next.next);

        ListNode temp = head.next;
        head.next = head;
        head.next.next = temp.next;
        temp.next = head; //现在temp是head
        head = temp;
        return head;
    }

    public static int imageSmootherADD(int[][] img, int i, int j){
        int sum = img[i][j];
        int divide = 9;
        try{
            sum+=img[i-1][j-1];
        }
        catch (Exception e){
            sum+=0;
            divide--;
        }

        try{
            sum+=img[i-1][j];
        }
        catch (Exception e){
            sum+=0;
            divide--;
        }
        try{
            sum+=img[i-1][j+1];
        }
        catch (Exception e){
            sum+=0;
            divide--;
        }
        try{
            sum+=img[i][j-1];
        }
        catch (Exception e){
            sum+=0;
            divide--;
        }
        try{
            sum+=img[i][j+1];
        }
        catch (Exception e){
            sum+=0;
            divide--;
        }
        try{
            sum+=img[i+1][j-1];
        }
        catch (Exception e){
            sum+=0;
            divide--;
        }
        try{
            sum+=img[i+1][j];
        }
        catch (Exception e){
            sum+=0;
            divide--;
        }
        try{
            sum+=img[i+1][j+1];
        }
        catch (Exception e){
            sum+=0;
            divide--;
        }
        return (int)sum/divide;
    }

    public static int[][] imageSmoother(int[][] img) {
        int column = img[0].length;
        int row = img.length;
        int[][] resimg = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                resimg[i][j] = imageSmootherADD(img,i,j);
            }
        }
        return resimg;
    }

    public static ListNode reverseList(ListNode head) { //head -1 -2 -3
        if(head==null || head.next == null){  //结束的条件
            return head;
        }
        ListNode temp = head;
        // 每一轮循环需要做什么？
        ListNode temp2 = reverseList(head.next);

        ListNode iterator = temp2;
        while(iterator.next != null){
            iterator = iterator.next;
        }

        iterator.next = temp;
        temp.next = null;
        return temp2;
    }

    public static ListNode reverseListNoRecursion(ListNode head) { //head 1-2-3-4
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> myset = new HashSet<>();
        for (int num : nums) {
            if (myset.contains(num)) return true;
            else myset.add(num);
        }
        return false;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> mymap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if(!mymap.containsKey(nums[i])){
                mymap.put(nums[i],new ArrayList<Integer>());
                mymap.get(nums[i]).add(i);
            }
            else {
                for (Integer integer : mymap.get(nums[i])) {
                    if(Math.abs(i-integer) <= k) return true;
                }
                mymap.get(nums[i]).add(i);
            }
        }
        return false;
    }

    public static List<String> summaryRanges(int[] nums) {
        if(nums.length ==0) return new ArrayList<String>();

        boolean seriesFlag = false;
        int start = 0;
        List<String> list = new ArrayList();
        if(nums.length ==1){
            list.add(new String(String.valueOf(nums[start])));
            return list;
        }

        for (int i = 0; i < nums.length-1; i++) {
            if(!seriesFlag) start = i;
            if(nums[i] == nums[i+1]-1){
                seriesFlag = true;
            }
            else {
                if(i!=start) list.add(new String(nums[start] + "->" + nums[i]));
                else list.add(new String(String.valueOf(nums[start])));
                seriesFlag = false;
            }
        }
        if(seriesFlag){
            list.add(new String(nums[start] + "->" + nums[nums.length-1]));
        }
        if(!seriesFlag && ( (nums[nums.length-1]-1) != nums[nums.length-2] ))
            list.add(new String(String.valueOf(nums[nums.length-1])));

        return list;
    }

    public static boolean isPowerOfTwo(int n) {
        if(n == 0) return true;
        while(n%2 == 0 && n>1){
            n/=2;
        }
        if(n==1) return true;
        return false;
    }

    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        if(nums.length ==1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int loopmax = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i];
            dp[i] = Math.max(dp[i], dp[i-1]+dp[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static int climbStairs(int n) {
        if(n==1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    public static int[] countBits(int n) {//比特位计数：分奇数偶数来看
        int[] dp = new int[n+1];
        if(n == 0){
            dp[0] = 0;
        }
        else {
            dp[1] = 1;
            for (int i = 2; i < dp.length; i++) {
                if(i%2 == 1){
                    dp[i] = dp[i-1]+1;
                }
                else {
                    dp[i] = dp[i/2];
                }
            }
        }
        return dp;
    }

    public static boolean isSubsequence(String s, String t) {
        //int[] dp = new int[s.length()];
        List<Integer> dp = new ArrayList<>();
        if(s.length()>t.length()) return false;
        if(s.length()==0 || s.equals(t)) return true;

        int j =0;
        for (int i = 0; i < t.length(); i++) {
            if(t.charAt(i) == s.charAt(j)) j++;

        }
        return false;
    }

    public static boolean isPalindrome(ListNode head) {//1-2-2-1  1-2-3-4-3-2-1
        if(head == null) return true;
        int num = 0;
        ListNode temp = head;
        while(temp!=null){
            num++;
            temp = temp.next;
        }

        int mid = num/2;
        temp = head;
        for (int i = 0; i < mid; i++) {
            temp = temp.next;
        }

        if(num%2==1) temp = temp.next;
        ListNode midnode = reverseListNoRecursion(temp);
        while(midnode!=null && head.val == midnode.val){
            head = head.next;
            midnode = midnode.next;
        }
        if(midnode == null) return true;
        return false;
    }

    public static List<String> generateParenthesis(int n) {
        //"(" + 【j=p时所有括号的排列组合】 + ")" + 【j=q时所有括号的排列组合】
        List<List<String>> reslist = new ArrayList<>();
        List<String> templist = new ArrayList<>();
        templist.add("");
        reslist.add(new ArrayList<>(templist));

        templist.clear();
        templist.add("()");
        reslist.add(new ArrayList<>(templist));
        if(n == 1)  return reslist.get(n);

        for (int i = 2; i <= n; i++) {
            templist.clear();
            for (int j = 0; j < i; j++) {
                for (String s: reslist.get(j)
                     ) {
                    for (String t: reslist.get(i-j-1)
                         ) {
                        templist.add("("+s+")"+t);
                    }
                }
            }
            reslist.add(new ArrayList<>(templist));
        }

        return reslist.get(n);
    }

    public static void deleteNode(ListNode node) {
        ListNode temp = node.next;
        node.val = temp.val;
        node.next = node.next.next;
    }

    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        Map<Character,Integer>smap = new HashMap<>();
        Map<Character,Integer>tmap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if(smap.containsKey(s.charAt(i))){
                smap.put(s.charAt(i), smap.get(s.charAt(i))+1);
            }
            else {
                smap.put(s.charAt(i),1);
            }
        }

        for (int i = 0; i < t.length(); i++) {
            if(tmap.containsKey(t.charAt(i))){
                tmap.put(t.charAt(i), tmap.get(t.charAt(i))+1);
            }
            else {
                tmap.put(t.charAt(i),1);
            }
        }

        if(smap.entrySet().equals(tmap.entrySet()))
                return true;
        else return false;
    }

    public static int addDigits(int num) {
        int sum=0;
        do {
            sum=0;
            while(num>0){
                sum+=num%10;
                num/=10;
            }
            num = sum;
        }
        while (sum<0 || sum>=10);

        System.out.println(sum);
        return sum;
    }

    public boolean isUgly(int n) {
        while(n%2==0){
            n/=2;
        }
        while(n%3==0){
            n/=3;
        }
        while(n%5==0){
            n/=3;
        }
        if(n==1) return true;
        else return false;
    }

    public int missingNumber(int[] nums) {
        int sum1 = Arrays.stream(nums).sum();
        int sum2=0;
        for (int i = 1; i <= nums.length; i++) {
            sum2+=i;
        }
        return  sum2-sum1;
    }

    public static void moveZeroes(int[] nums) {
        for (int i = nums.length-1; i >= 0; i--) {
            if(nums[i]==0){
                for (int j = i; j < nums.length-1; j++) {
                    nums[j] = nums[j+1];
                }
                nums[nums.length-1] = 0;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    //dp
    public static int jump(int[] nums) { // nums = [2,3,1,1,4]
        if(nums.length==1) return 0;
        if(nums.length==2) return 1;
        int currentJump = 0;
        int[] dp = new int[nums.length];//下标为i的到达末尾的最少次数
        Arrays.fill(dp,100000);
        for (int i = nums.length-2; i >=0;  i--) {
            currentJump = i+nums[i];
            if(currentJump >= nums.length-1) dp[i] = 1;
            else {
                for (int j = i; j <= currentJump; j++) {
                    dp[i] = Math.min(dp[i], 1+ dp[j]);
                }
            }
        }
        return dp[0];
    }

    //
    public static boolean canJump(int[] nums) {
        if(nums.length==1) return true;
        if(nums.length==2) {
            if(nums[0]>=1) return true;
            else return false;
        }
        int currentJump = 0;
        boolean[] dp = new boolean[nums.length];//下标为i的到达末尾的最少次数
        Arrays.fill(dp,false);
        for (int i = nums.length-2; i >=0;  i--) {
            currentJump = i+nums[i];
            if(currentJump >= nums.length-1) dp[i] = true;
            else {
                for (int j = i; j <= currentJump; j++) {
                    dp[i] = dp[i]||dp[j];
                }
            }
        }
        return dp[0];
    }

    public static int uniquePaths(int m, int n) {
        if(m<=1 || n<=1) return 1;
        int[][] dp = new int[m][n];
        dp[0][0] = 0;
        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }

        dp[0][1] = 1;
        dp[1][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //if(m<=1 || n<=1) return 1;
        int[][] dp = new int[m][n];
        dp[0][0] = 0;
        int flag = 0;
        for (int i = 1; i < n; i++) {
            if(obstacleGrid[0][i]!=1)
            dp[0][i] = 1-flag;
            else {
                dp[0][i] = 0;
                flag=1;
            }
        }
        flag = 0;
        for (int i = 1; i < m; i++) {
            if(obstacleGrid[i][0]!=1)
            dp[i][0] = 1-flag;
            else {
                dp[i][0] = 0;
                flag = 1;
            }
        }

        if(m<=1 || n<=1) return dp[m-1][n-1];


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(obstacleGrid[i][j]!=1)
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
                else {
                    dp[i][j]=0;
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //if(m<=1 || n<=1) return 1;
        int[][] dp = new int[m][n];
        int[] ten=new int[n];
        Arrays.fill(ten, 10000);
        Arrays.fill(dp,ten);

        dp[0][0] = grid[0][0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1]+grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    public static int numDecodings(String s) {//s是数字
        if(s.length()==0 || s.charAt(0)=='0') return 0;
        if(s.length()==1) return 1;

        int[] dp = new int[s.length()];
        dp[0] = 1;// 注意这里0是指第1个
        dp[1] = 2;
        if(Integer.valueOf(s.substring(0,2)) >26){
            dp[1] --;
        }
        else if(s.charAt(1)=='0'){
            dp[1]--;
        }
        if(s.length()==2) return dp[1];

        for (int i = 2; i < s.length(); i++) {
            dp[i] = 0;
            if(Integer.valueOf("" + s.charAt(i))!=0)
                dp[i] += dp[i-1]; //单独考虑
            String temp = s.charAt(i-1)+""+s.charAt(i);
            if(temp.equals("00")){
                return 0;
            }
            int doubleword = Integer.valueOf(temp);
            if( (doubleword<=26 && doubleword>0)&& s.charAt(i-1)!='0'){
                dp[i] +=dp[i-2];
            }
        }
        return dp[s.length()-1];
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s3.equals("")){
            if(s1.equals("")&&s2.equals("")){
                return true;
            }
            else return false;
        }
        if(s1.equals("")||s2.equals("")){
            if(s1.equals(s3)||s2.equals(s3)) return true;
            else return false;
        }
        if(s1.charAt(0)!=s3.charAt(0)) return false;
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for (int i = 1; i <= s1.length(); i++) {
            if(s1.substring(0,i).equals(s3.substring(0,i)))
                dp[i][0] = true;
            else dp[i][0] = false;
        }

        for (int i = 1; i <= s2.length(); i++) {
            if(s2.substring(0,i).equals(s3.substring(0,i)))
                dp[0][i] = true;
            else dp[0][i] = false;
        }

        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if(s1.charAt(i-1) == s3.charAt(i+j-1) && dp[i-1][j]){
                    dp[i][j] = true;
                }
                else dp[i][j]=false;

                if(s2.charAt(i-1) == s3.charAt(i+j-1) && dp[i][j-1]){
                    dp[i][j] = true;
                }
                else dp[i][j]=false;

            }
        }

        return dp[s1.length()][s2.length()];
    }

    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        if(prices.length<=1) return 0;
        if(prices.length==2) return prices[1]>prices[0]?(prices[1]-prices[0]):0;
        dp[0] = 0;
        dp[1] = prices[1]>prices[0]?(prices[1]-prices[0]):0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1] + (prices[i] > prices[i-1]?(prices[i] - prices[i-1]):0);
        }
        return dp[dp.length-1];
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()<=1) return triangle.get(0).get(0);
        int dp[][] = new int[triangle.size()][triangle.size()];

        dp[0][0] = triangle.get(0).get(0);
        dp[1][0] = triangle.get(1).get(0) + dp[0][0];
        dp[1][1] = triangle.get(1).get(1) + dp[0][0];

        if(triangle.size()==2) return Math.min(dp[1][0],dp[1][1]);
        //index = triangle.get(1).get(0)<triangle.get(1).get(1)?0:1;

        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j <= i; j++) {
                if(j==0){
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                }
                else if(j==i){
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1],dp[i-1][j])+triangle.get(i).get(j);
                }
            }
        }
        int resmin = Integer.MAX_VALUE;
        for (int i = 0; i < dp[dp.length - 1].length; i++) {
            if (resmin < dp[dp.length-1][i]){
                resmin = dp[dp.length-1][i];
            }
        }
        return resmin;
    }

    public static Boolean isPalindromeString (String s){
        if(s.length()==1) return true;
        StringBuffer stringBuffer = new StringBuffer(s);
        String two = stringBuffer.reverse().toString();
        if(s.equals(two)) return true;
        else return false;
    }

    //131: 动态规划废案
//    public static List<List<String>> partition(String s) {// abb aab
//        List<List<String>> resList = new ArrayList<>();
//        //List<String> tempList = new ArrayList<>();
//        List<String> iniList = new ArrayList<>();
//        List<List<List<String>>> dp = new ArrayList<>();
//        int flag = 0;
//
//        if (s.length()==1) {
//            List sololist = new ArrayList<>();
//            sololist.add(s);
//            resList.add(sololist);
//            return resList;
//        }
//
//
//        iniList.add(s.charAt(0)+"");
//        resList.add(iniList);
//        dp.add(resList);
//
//        for (int i = 1; i < s.length(); i++) {
//            dp.add(dp.get(i-1));
//            for (int n = 0; n < dp.get(i).size(); n++) {
//                dp.get(i).get(n).add(""+s.charAt(i));
//            }
//
//
//            for (int k =i-1; k >= 0; k--) {
//                if(isPalindromeString(s.substring(k,i+1))){
//                    for (List<String> strings : dp.get(k - 1)) { //对于k-1形式中的每个分隔方式  “字符串集合”, 后面都加上
//                        List<String> tempStrings = strings.stream().collect(Collectors.toList());
//                        dp.get(i).add(tempStrings);
//                    }
//                }
//            }
//        }
//        return dp.get(s.length()-1);
//    }

    public static void partitionCursion(List<List<String>> reslist, List<String> path, String s, int stop){
        if(stop == s.length()) {
            reslist.add(new ArrayList<>(path));
            return;
        }
        for (int i = stop+1; i <= s.length(); i++) {
            if(isPalindromeString(s.substring(stop,i))){
                path.add(s.substring(stop,i));
                partitionCursion(reslist,path,s, i);
                path.remove(path.size()-1);
            }
        }
    }

    public static List<List<String>> partition(String s){ // aab
        List<List<String>> reslist = new ArrayList<>();
        if (s.length()==1) {
            List<String> sololist = new ArrayList<>();
            sololist.add(s);
            reslist.add(sololist);
            return reslist;
        }
        List<String> path = new ArrayList<>();
        partitionCursion(reslist, path, s, 0);
        System.out.println(reslist.toString());
        return reslist;
    }

    public static void recursion_permute(int[] nums, boolean[] log, List<List<Integer>> reslist, Deque<Integer> templist, int depth){
        if(depth == nums.length) {
            reslist.add(new ArrayList<>(templist));
            return ;
        }

        for (int i = 0; i < nums.length; i++) {
            if(!log[i]) {
                templist.addLast(nums[i]);
                log[i] = true;
                recursion_permute(nums, log, reslist, templist, depth++);
                log[i] = false;
                templist.removeLast();
            }
        }
        return ;
    }

    public static List<List<Integer>> permute(int[] nums) { //1,2,3
        int len = nums.length;
        List<List<Integer>> reslist = new ArrayList<>();
        Deque<Integer> templist = new ArrayDeque<>(len);
        boolean[] visited = new boolean[len];
        Arrays.fill(visited, false);
        recursion_permute(nums, visited, reslist, templist, 0);
        return reslist;
    }

    public static void recursion_permute_unique(int[] nums, boolean[] log,List<Integer> path, List<List<Integer>> reslist, int depth){
        if(depth == nums.length) {
            reslist.add(new ArrayList<>(path));
            return ;
        }

        for (int i = 0; i < nums.length; i++) {
            if(i>0 && nums[i]==nums[i-1] && log[i-1]==false) continue;
            if(!log[i]){
                path.add(nums[i]);
                log[i] =true;
                recursion_permute_unique(nums, log, path, reslist,depth+1);
                log[i] = false;
                path.remove(path.size()-1);
            }
        }
        return ;
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> reslist = new ArrayList<>();
        boolean[] log = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        recursion_permute_unique(nums, log, path, reslist, 0);
        System.out.println(reslist.toString());
        return reslist;
    }

    public static boolean wordPattern(String pattern, String s) {
        //List<String> strings = new ArrayList<>(s.split(" "));
        String[] strings = s.split(" ");
        if(strings.length != pattern.length()) return false;

        Map<Character,String> map1 = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if(!map1.containsKey(pattern.charAt(i))){
                map1.put(pattern.charAt(i),strings[i]);
            }
            else {
                if(map1.get(pattern.charAt(i)).equals(strings[i])) continue;
                else {
                    return false;
                }
            }
        }

        Map<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            if(!map2.containsKey(strings[i])){
                map2.put(strings[i], pattern.charAt(i));
            }
            else {
                if(map2.get(strings[i])==pattern.charAt(i)) continue;
                else {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean canWinNim(int n) {
        if(n <= 3) return true;
        if(n%(3+1)==0) return false;
        else return true;
    }

    public static boolean isPowerOfThree(int n) {
        if(n<=0) return false;
        while(n%3==0) n/=3;
        if(n==1) return true;
        else return false;
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> reslist = new ArrayList<>();
        List<Integer> templist = new ArrayList<>();

        if(candidates.length==1) {
            if(target%candidates[0]!=0)
                 return reslist;
            else {
                do
                {
                templist.add(candidates[0]);
                target-=candidates[0];
                }
                while (target>0);
                reslist.add(templist);
                return reslist;
            }
        }

        combinationSumCursion(reslist, templist ,candidates, 0,target,0, 0);
        //boolean[] log = new boolean[candidates.length];
        System.out.println(reslist.toString());
        return reslist;
    }

    public static void combinationSumCursion(List<List<Integer>> reslist, List<Integer> path,int[] candidates, int depth, int target,int total, int begin){
        if(total == target){
            reslist.add(new ArrayList<>(path));
            return;
        }
        if(total>target) return;
//        if(depth==candidates.length)
//            return;

        for (int i = begin; i < candidates.length; i++) {
            path.add(candidates[i]);
            total+= candidates[i];
            combinationSumCursion(reslist, path, candidates, depth+1, target, total, i);
            total-= candidates[i];
            path.remove(path.size()-1);
//            if(depth==0) begin++;
        }
    }

    public static void combinationSumCursion2(List<List<Integer>> reslist, List<Integer> path,int[] candidates, int depth, int target,int total, boolean[] log, int begin){
        if(total == target){
            reslist.add(new ArrayList<>(path));
            return;
        }
        if(total>target||depth==candidates.length) return;

        for (int i = begin; i < candidates.length; i++) {
            //if(!log[i]){
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
                path.add(candidates[i]);
                total+= candidates[i];
                log[i] = true;
                combinationSumCursion2(reslist, path, candidates, depth+1, target, total, log, i+1);
                log[i] = false;
                total-= candidates[i];
                path.remove(path.size()-1);
            //}
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> reslist = new ArrayList<>();
        List<Integer> templist = new ArrayList<>();

        if(candidates.length==1) {
            if(target!=candidates[0])
                return reslist;
            else {
                templist.add(candidates[0]);
                reslist.add(templist);
                return reslist;
            }
        }
        boolean[] log = new boolean[candidates.length];
        Arrays.sort(candidates);
        combinationSumCursion2(reslist, templist ,candidates, 0, target,0, log, 0);

        System.out.println(reslist.toString());
        return reslist;
    }

    public static void combinedfs(int[] nums, int k, int depth, List<List<Integer>> reslist, List<Integer> path, int begin){
        if(depth==k){
            reslist.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            combinedfs(nums,k,depth+1,reslist,path,i+1);
            path.remove(path.size()-1);
        }
    }

    public static List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        List<List<Integer>> reslist = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] log = new boolean[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i+1;
        }
        combinedfs(nums,k,0,reslist,path,0);
        System.out.println(reslist.toString());
        return reslist;
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> reslist = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        reslist.add(path);

        for (int k = 1; k <= nums.length; k++) {
            combinedfs(nums,k,0,reslist,path,0);
        }
        System.out.println(reslist.toString());
        return reslist;
    }

    public static void subsetsWithDupdfs(int[] nums, int k, int depth, List<List<Integer>> reslist, List<Integer> path, int begin){
        if(depth==k){
            reslist.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            if(i>begin && nums[i] == nums[i-1]) continue;
            path.add(nums[i]);
            combinedfs(nums,k,depth+1,reslist,path,i+1);
            path.remove(path.size()-1);
        }
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> reslist = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int k = 0; k <= nums.length; k++) {
            subsetsWithDupdfs(nums,k,0,reslist,path,0);
        }
        System.out.println(reslist.toString());
        return reslist;
    }

    public static void letterCombinationsCursion(List<String> reslist, String digits, StringBuilder path, int depth, List<String> seed) {
        if(depth == digits.length()) {
            reslist.add(new String(path.toString()));
            return;
        }
        String tempstr = seed.get((int)(digits.charAt(depth)-'2'));

        for (int i = 0; i < tempstr.length(); i++) {
            path.append(tempstr.charAt(i));
            letterCombinationsCursion(reslist, digits, path, depth+1, seed);
            path.deleteCharAt(path.length()-1);
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> reslist = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        List<String> seed = new ArrayList<>();
        seed.add("abc");
        seed.add("def");
        seed.add("ghi");
        seed.add("jkl");
        seed.add("mno");
        seed.add("pqrs");
        seed.add("tuv");
        seed.add("wxyz");
        int depth = 0;
        letterCombinationsCursion(reslist, digits, path, depth, seed);
        System.out.println(reslist.toString());
        return reslist;
    }

    public static boolean IpJudge(String ip){
        if(ip.length()>3 || ip.length()<0) return false;
        if(ip.length()>=2 && ip.charAt(0)=='0') return false;
        if(Integer.valueOf(ip)>255 || Integer.valueOf(ip)<0) return false;
        return true;
    }

    public static void restoreIpAddressesCursion(String s, List<String> reslist, StringBuilder path, int depth, int stop){
        if(depth==4){
            if(stop==s.length()) {
                reslist.add(new String(path.substring(0,path.length()-1).toString()));
            }
            else {
                return;
            }
        }
        for (int i = stop+1; i <= s.length()&& (i-stop)<=3; i++) {
            if(IpJudge(s.substring(stop, i))){
                path.append(s.substring(stop, i));
                int len = s.substring(stop, i).length();
                path.append('.');
                restoreIpAddressesCursion(s, reslist, path, depth+1, i);
                path.deleteCharAt(path.length()-1);
                path.delete(path.length()-len,path.length());
            }
        }
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> reslist = new ArrayList<>();
        if(s.length()<4 || s.length()>12) return reslist;
        int depth = 0, stop = 0;
        StringBuilder path = new StringBuilder();
        restoreIpAddressesCursion(s, reslist, path, depth,stop);
        System.out.println(reslist.toString());
        return reslist;
    }

    public static void reverseString(char[] s) {//1 2 3 4
        int i = 0;
        int j = s.length-1;
        char temp;
        while(i<j){
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
        System.out.println(Arrays.toString(s));
    }

    public static String reverseVowels(String s) {
        Character[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
        StringBuilder sb = new StringBuilder(s);
        List<Character> vow = Arrays.asList(vowels);
        List<Integer> indexlist = new ArrayList<>();
        for (int k = 0; k < s.length(); k++) {
            if(vow.contains(s.charAt(k))){
                indexlist.add(k);
            }
        }
        if(indexlist.size()<=1) return s;

        int i = 0;
        int j = indexlist.size()-1;
        char tempi, tempj;
        while (i<j){ // hello
            tempi = sb.charAt(indexlist.get(i));
            tempj = sb.charAt(indexlist.get(j));
            sb.deleteCharAt(indexlist.get(i));
            sb.insert(indexlist.get(i),tempj+"");
            sb.deleteCharAt(indexlist.get(j));
            sb.insert(indexlist.get(j),tempi+"");
//            sb.replace(indexlist.get(i),indexlist.get(i),sb.charAt(j)+"");
//            sb.replace(indexlist.get(j),indexlist.get(j),temp+"");
            i++;
            j--;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

























}

