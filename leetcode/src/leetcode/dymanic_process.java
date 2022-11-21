package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class dymanic_process {
    public static int lastStoneWeight(int[] stones) {
        List<Integer> list=new ArrayList<Integer>();
        for (int element: stones) {
            list.add(element);
        }

        int a=0,b=0,c=0;

        while (list.size()>=2){
             a = list.stream().max(new Comparator<Integer>() {
                 @Override
                 public int compare(Integer o1, Integer o2) {
                     return o1-o2;
                 }
             }).get();
             list.remove(list.indexOf(a));

            b = list.stream().max(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            }).get();
            list.remove(list.indexOf(b));

             c = Math.abs(a-b);
             if(c!=0){
                 list.add(c);
             }
        }
        if(list.size()==1) return list.get(0);
        else return 0;
    }

    public static int lastStoneWeightII(int[] stones) {
        int stonessum = Arrays.stream(stones).sum();
        int half = stonessum/2;
        int[][] dp = new int[stones.length][half+1];
        for (int i = 0; i < stones.length; i++) {
            dp[i][0] = 0;
        }
        for (int j = stones[0]; j < half+1; j++) {
            dp[0][j] = stones[0];
        }

        for (int i = 1; i < stones.length; i++) {
            for (int j = 1; j < half+1; j++) {
                if(j>=stones[i])
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j], dp[i-1][j-stones[i]]+stones[i]));
                else dp[i][j] = dp[i-1][j];
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        return stonessum-dp[stones.length-1][half]*2;
    }

    public static int findTargetSumWays(int[] nums, int target) {
        //  dp[i][j] 表示：从[0,i]填满j（包括j）这么大容积的包，有dp[i][j]种方法;
        int sum = Arrays.stream(nums).sum();
        if((target+sum)%2==1) return 0;
        int left = (target+sum)/2;

//        int[][] dp = new int[nums.length+1][left+1];
//        dp[0][0] = 1;
//
//
////背包问题的循环实质是在考虑：针对固定容量j的背包，新遍历到的元素nums[i]到底选不选？
////一方面是不选新的，那么dp[i][j]=dp[i-1][j],  新的元素没用上【组合问题】或者超标了【最大问题】，保持旧的不变
////如果选了新的，那么这里的组合方式变成了，在不管j-nums的前提下能有的组合方式
//        for (int i = 1; i < nums.length; i++) {
//            int num = nums[i-1];
//            for (int j = 0; j < left+1; j++) {
//                dp[i][j] = dp[i-1][j];
//                if(j>=num)
//                    dp[i][j] += dp[i][j-num];
//            }
//        }
//        return dp[nums.length][left];
        int[] dp = new int[left+1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i] ; j--) {
                dp[j] += dp[j-nums[i]];
            }
        }
        return dp[left];
    }

    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < amount+1 ; j++) {
                if(j < coins[i]) dp[j] = dp[j];
                else {
                    dp[j] += dp[j-coins[i]];
                }
            }
        }
        return dp[amount];
    }

    // 13:  2  5

    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int j = 0; j < target+1 ; j++) {
            for (int i = 0; i < nums.length; i++) {
                if(j < nums[i]) dp[j] = dp[j];
                else {
                    dp[j] += dp[j-nums[i]];
                }
            }
        }
        return dp[target];
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = max;
        }
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j < amount+1; j++) {
                if (dp[j - coins[i]] != max)
                    dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
            }
        }
        return dp[amount]==max?-1:dp[amount];
    }

    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        List<Integer> coinlist = new ArrayList();
        for (int i = 1; Math.pow(i,2) <= n; i++) {
            coinlist.add(i*i);
        }
        int len = coinlist.size();
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        System.out.println(coinlist.toString());

        for (int i = 0; i < len; i++) {
            for (int j = coinlist.get(i); j < n+1; j++) {
                if(dp[j-coinlist.get(i)]!=Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j-coinlist.get(i)]+1,dp[j]);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public static int findMaxForm(String[] strs, int m, int n) {
//        输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//        输出：4
//        解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
        int len = strs.length;
        int[] zero = new int[len];
        int[] one = new int[len];
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                if(strs[i].charAt(j) == '1'){
                    one[i]++;
                }
                else zero[i]++;
            }
        }
        int[][] dp = new int[m+1][n+1];//dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。
        dp[0][0] = 0;

        for (int k=0; k<strs.length; k++) {
            for (int i = m; i >= zero[k]; i--) {
                for (int j = n; j >= one[k]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-zero[k]][j-one[k]]+1);
                }
            }
        }
        return dp[m][n];
    }

    public static int rob(int[] nums) {
        //考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]。
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[dp.length-1];
    }

    public static int rob2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if(nums.length==1) return dp[0];

        dp[1] = Math.max(nums[0], nums[1]);
        if(nums.length==2) return dp[1];

        dp[2] = Math.max(nums[0],Math.max(nums[1],nums[2]));
        if(nums.length==3) return dp[2];

        int w1 = rob(Arrays.copyOfRange(nums,0,nums.length-1));
        int w2 = rob(Arrays.copyOfRange(nums,1,nums.length));
        int w3 = rob(Arrays.copyOfRange(nums,1,nums.length-1));

        int wealth = Math.max(w1,Math.max(w2,w3));
        System.out.println(wealth);
        return wealth;
    }

    public static int maxProfit(int[] prices) {
        int profit = 0;
        int[] dp = new int[prices.length+1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1], prices[i-1] - Arrays.stream(prices).limit(i-1).min().getAsInt());
        }
        System.out.println(Arrays.toString(dp));
        return dp[prices.length];
    }

    public static int maxProfit2(int[] prices) {
        int[][] dp = new int[prices.length][2];
//        dp[i][0] 表示第i天持有股票所得最多现金。
//        dp[i][1] 表示第i天不持有股票所得最多现金
        dp[0][0] = 0 - prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            // 今天持股，有两种可能：原本有股——今天没卖【卖了又买没有意义】， 原本没股——今天买入
            dp[i][0] = Math.max(dp[i-1][1]-prices[i] , dp[i-1][0]);
            // 今天没持股，有两种可能：原本有股——今天卖了， 原本没股——今天买入
            dp[i][1] = Math.max(dp[i-1][0]+prices[i], dp[i-1][1]);
        }

        return  dp[prices.length][1];
    }

    public static int maxProfit3(int[] prices) {
//        确定dp数组以及下标的含义
//        一天一共就有五个状态，
//        没有操作
//        第一次买入
//        第一次卖出
//        第二次买入
//        第二次卖出
//        dp[i][j]中 i表示第i天，j为 [0 - 4] 五个状态，dp[i][j]表示第i天状态j所剩最大现金，这是“状态”而不是“操作”。
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for (int i = 1; i < prices.length; i++) {
            // 保持没有操作的状态：
            dp[i][0] = dp[i-1][0];
            // 进入第一次买入的状态，有可能是之前买了今天啥也没做，也可能是今天刚买的
            dp[i][1] = Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
            // 第一次卖出的状态，原来有恰好被卖了，或者是已经被卖了，今天啥都没做
            dp[i][2] = Math.max(dp[i-1][1]+prices[i], dp[i-1][2]);
            // 第二次买入的状态，之前就买了，或者今天刚买【】
            dp[i][3] = Math.max(dp[i-1][2]-prices[i], dp[i-1][3]);
            // 第二次卖出的状态，之前就卖了，或者今天刚卖【】
            dp[i][4] = Math.max(dp[i-1][3]+prices[i], dp[i-1][4]);
        }

        return  dp[prices.length-1][4];
    }

    public static int maxProfit4(int k, int[] prices){
        if(prices.length==0||k==0){
            return 0;
        }
        int[][] dp = new int[prices.length][1+2*k];

        for (int i = 0; i < 2*k+1; i++) {
            if(i%2==0) dp[0][i] = 0;
            else dp[0][i] = 0-prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i-1][0];
            for (int j = 1; j < 2*k+1; j++) {
                if(j%2 == 1)
                    dp[i][j] = Math.max(dp[i-1][j-1]-prices[i],dp[i-1][j]);
                else dp[i][j] = Math.max(dp[i-1][j-1]+prices[i],dp[i-1][j]);
            }
        }

        return dp[prices.length-1][2*k];
    }

    public static int maxProfitice(int[] prices) {//规定一天只能有一个操作
        int[][] dp = new int[prices.length][4];

//        dp[i][0] 手上有股票的状态，
//        dp[i][1] 手上没有股票的状态a，今天刚刚卖
//        dp[i][2] 手上没有股票的状态b，已经卖了好几天了，不在冷静期
//        dp[i][3] 手上没股票的状态c，在冷静期，卖后第一天

        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;

        for (int i = 0; i < prices.length; i++) {
            // 今天持股，有两种可能：原本有股——今天没卖， 原本没股——今天买入【昨天是冷静期或者越过冷静期了】
            dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][3],dp[i-1][2])-prices[i]);

            //手上没有股票的状态a，今天刚刚卖
            dp[i][1] = dp[i-1][0]+prices[i];

            //手上没有股票的状态b，已经卖出了好几天了，不在冷静期!
            dp[i][2] = Math.max(dp[i-1][2],dp[i-1][3]);

            //手上没股票的状态c，在冷静期，卖后第一天
            dp[i][3] = dp[i-1][1];

        }
        return Math.max(dp[prices.length-1][1],Math.max(dp[prices.length-1][2],dp[prices.length-1][3]));
    }

    public static int maxProfit5(int[] prices, int fee) {
//        你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
//        返回获得利润的最大值。
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0; //手上没有股票
        dp[0][1] = -prices[0]; //手上有股票
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]-fee); //第i天的手上没有股票：昨天有今天卖了，或者一直没卖
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);//第i天的手上有股票：昨天就有，或者今天买入
        }
        System.out.println(dp[prices.length-1][0]);
        return dp[prices.length-1][0];
    }

    public static int lengthOfLIS(int[] nums) {
//        找到其中最长严格递增子序列的长度。
//        子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
        // [4,10,4,3,8,9]
        // [10,9,2,5,3,7,101,18]
        int[] dp = new int[nums.length]; //以i结尾的最长序列
        Arrays.fill(dp,1);
        dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {//
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static int findLengthOfLCIS(int[] nums) { //[1,3,5,4,7]
        int[] dp = new int[nums.length]; //以i结尾的最长序列
        Arrays.fill(dp,1);
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>nums[i-1]) dp[i] = dp[i-1]+1;
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length]; //dp[i][j]表示以nums1[i]结尾 nums2[j]结尾 之间的公共的 、长度最长的子数组的长度
        if(nums1[0] == nums2[0]) dp[0][0] = 1;
        else dp[0][0] = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if(nums1[i]!=nums2[j]) dp[i][j] = 0;
                else {
                    if(i>=1&&j>=1)
                    dp[i][j] = dp[i][j] + dp[i-1][j-1] + 1;
                    else dp[i][j] = 1;
                }
            }
        }

        //nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]

        int max = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if(dp[i][j]>max){
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()]; //dp[i][j]表示以nums1[i]结尾 nums2[j]结尾之间的公共的 、长度最长的子串的长度
        int flag = 0;

        for (int i = 0; i < text1.length(); i++) {
            if(text1.charAt(i) == text2.charAt(0))
                flag = 1;
            dp[i][0] = flag;
        }

        flag = 0;
        for (int i = 0; i < text2.length(); i++) {
            if(text2.charAt(i) == text1.charAt(0))
                flag = 1;
            dp[0][i] = flag;
        }

        for (int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                if(text1.charAt(i)!=text2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i-1][j-1]+ 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if(dp[i][j]>max){
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        //dp[i][j]：长度为[0, i - 1]的nums1与长度为[0, j - 1]nums2的最长公共子序列为dp[i][j]
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        int begin = 1;
        for (int i = 1; i <= nums1.length; i++) {   //【0到i-1】
            for (int j = 1; j <= nums2.length; j++) {
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
        return dp[nums1.length][nums2.length];
    }

    public static int numDistinct(String s, String t) {
        int[][] dp = new int[t.length()+1][s.length()+1]; //dp[i][j] [0, i-1] [0, j-1]
        for (int i = 1; i <= t.length(); i++) { // 被审阅的
            for (int j = i; j <= s.length() ; j++) {  // 主串
                if(i==1){
                    if(s.charAt(j-1) == t.charAt(i-1)){
                        dp[i][j] = dp[i][j-1]+1;
                    }
                    else dp[i][j] = dp[i][j-1];
                    continue;
                }

                if(s.charAt(j-1) == t.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1]+dp[i][j-1];
                }
                else dp[i][j] = dp[i][j-1];
            }
        }
        return dp[s.length()][t.length()];
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        int commonseq = dp[word1.length()][word2.length()];
        return word1.length()+word2.length()-commonseq*2;
    }

    public static int minDistance2(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        //将[0,i-1]变成[0,j-1]所需要的步骤；
        if(word1.length()==0 || word2.length()==0)
            return Math.abs(word1.length()-word2.length());

        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] =  i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if(word1.charAt(i-1) != word2.charAt(j-1)){
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
                }
                else {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        System.out.println(dp[word1.length()][word2.length()]);
        return dp[word1.length()][word2.length()];
    }

    public static boolean huiwen(String s){
        if(s.length()==1) return true;
        int i,j;
        i = 0;
        j = s.length()-1;
        while (i<j){
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }
            else return false;
        }
        return true;
    }

    public static int countSubstrings(String s) { //  a b c c
        if(s.length()==0) return 0;
        if(s.length()<=1) return 1;
        boolean[][] dp = new boolean[s.length()][s.length()];
        dp[0][0] = true;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j ; i++) {
                if(s.charAt(i) == s.charAt(j)){
                    if(j-i>=3)
                        dp[i][j] = dp[i+1][j-1];
                    else{
                        dp[i][j] = true;
                    }
                }
                else dp[i][j] = false;
            }
        }
        int res= 0 ;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if(dp[i][j]) res++;
            }
        }
        System.out.println(Arrays.toString(dp));
        return res;
    }

    public static int longestPalindromeSubseq(String s) { //bbbab
        int[][] dp = new int[s.length()+1][s.length()+1];
//        StringBuilder sb = new StringBuilder(s);
//        String s1 = sb.reverse().toString();
//        dp[0][0] = 0;
//
//        for (int i = 1; i <= s.length(); i++) {
//            for (int j = 1; j <= s1.length(); j++) {
//                if(s.charAt(i-1) == s1.charAt(j-1)){
//                    dp[i][j] = dp[i-1][j-1]+1;
//                }
//                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
//            }
//        }
// dp[i][j]：字符串s在[i, j]范围内最长的回文子序列的长度为dp[i][j]。
        for (int i = s.length()-1; i >= 0 ; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1]+2;
                }
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], Math.max(dp[i][j], dp[i][j - 1]));
                }
            }
        }
        return dp[0][s.length()-1];
    }

    public static String longestPalindrome(String s) {
//        StringBuilder sb = new StringBuilder(s);
//        String s1 = sb.reverse().toString();
//        int[][] dp = new int[s.length()+1][s.length()+1];
        int len = s.length();
        int ans = 0, maxtemp = 0, maxleft = 0, maxright = 0;
        for (int i = 0; i < 2 * len - 1; i++) {
            int left = i / 2, right = left + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)){
                ans = right - left + 1;
                left--;
                right++;
            }

            if(maxtemp<ans) {
                maxtemp = ans;
                maxleft = left+1;
                maxright = right-1;
            }
            ans = 0;
        }
        return s.substring(maxleft,maxright+1);
    }
























}
