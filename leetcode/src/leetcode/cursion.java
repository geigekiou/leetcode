package leetcode;

import java.util.Arrays;
import java.util.List;

public class cursion {
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static boolean BoardJudge(int rowlen, int columnlen, int row, int column){
        if(row>=0 && row<rowlen && column>=0 && column<rowlen) return true;
        else return false;
    }

    public static boolean existCursion(char[][] board, String word, int row, int column, int depth, boolean[][] log){
        if(depth == word.length()-1)
            return board[row][column] == word.charAt(depth);
        if(board[row][column] == word.charAt(depth)){
            log[row][column] = true;
            for (int[] direction:DIRECTIONS
                ) {
                int new_row = row + direction[0];
                int new_column = column + direction[1];
                if(BoardJudge(board.length, board[0].length, new_row, new_column) && !log[new_column][new_row])
                    if(existCursion(board,word,new_row,new_column,depth+1,log)) return true;
                }
            log[row][column] = false;
        }
        return false;
    }

    public static boolean exist(char[][] board, String word) {
        boolean log[][] = new boolean[board.length][board[0].length];
        int depth = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(existCursion(board, word, i, j, 0, log)) return true;
            }
        }
        return false;
    }

    public static boolean solveJudge(int m, int n, int x, int y){
        if(x >= 0 && x < m && y >= 0 && y < n) return true;
        else return false;
    }

    public static void solveHelper(char[][] board, int x, int y, boolean[][] log){
        if(board[x][y] != '0' || log[x][y]) return;
        log[x][y] = true;
        for (int[] direction : DIRECTIONS) {
            int newx = x+direction[0];
            int newy = y+direction[1];
                if(solveJudge(board.length,board[0].length,newx,newy)&&!log[newx][newy]){
                    solveHelper(board,newx,newy,log);
                }
        }
    }

    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] log= new boolean[m][n];
        if(m<=2 || n<=2) return;
        for (int i = 0; i < m; i++) {
            if(i==0||i==m-1){
                for (int j = 0; j < n; j++) {
                    solveHelper(board,i,j,log);
                }
            }
            else
            {
                solveHelper(board,i ,0, log);
                solveHelper(board,i,n-1,log);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j]=='0'&& !log[i][j])
                    board[i][j]='X';
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

    }

    public static void IslandCursion(char[][] grid, boolean[][] log, int x, int y){
        if(grid[x][y] != '1' || log[x][y]) return;
        log[x][y] = true;
        for (int[] direction : DIRECTIONS) {
            int newx = x+direction[0];
            int newy = y+direction[1];
            if(solveJudge(grid.length,grid[0].length,newx,newy)&&!log[newx][newy]){
                IslandCursion(grid,log,newx,newy);
            }
        }
    }

    public static int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] log= new boolean[m][n];
        int nums=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!log[i][j] && grid[i][j]=='1'){
                    IslandCursion(grid,log,i,j);
                    nums++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(log[i]));
        }
        System.out.println(nums);
        return nums;
    }

//    public static boolean wordBreakHelper(String s, int begin, List<String> wordDict){
//        if(begin==s.length()){
//            return true;
//        }
//        for (int i = begin+1; i <= s.length(); i++) {
//            if(wordDict.contains(s.substring(begin,i))){
//                if(wordBreakHelper(s,i,wordDict))
//                    return true;
//            }
//        }
//        return false;
//    }F
    //" applepenapple", wordDict = ["apple", "pen"]
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = false;

        for (int i = 0; i < wordDict.size(); i++) {
            if(s.startsWith(wordDict.get(i))){
                dp[wordDict.get(i).length()] = true;
            }
        }
        //之前取了一个startindex，从这里开始遍历，这其实不对，前面可能会有一些被漏掉了；

        for (int i = 1; i < dp.length; i++) {
            if(!dp[i]){
                for (String s1 : wordDict) {
                    if(i > s1.length()){
                        if(dp[i-s1.length()]&& s.substring(i-s1.length(),i).equals(s1)){
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[s.length()];
    }

    public static int integerBreak(int n) {//10 = 3 + 3 + 4, 3 × 3 × 4 = 36
        int resnum = 0;
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i],Math.max((i-j)*j,  dp[j]*(i-j)));
            }
        }
        return dp[n];
    }

    public static int numTrees(int n) {
        if(n==1) return 1;
        int dp[] = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) { //j作为根结点的话
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        System.out.println(dp[n]);
        return dp[n];
    }

    public static boolean canPartition(int[] nums) {
        int sumnum = Arrays.stream(nums).sum();
        if(sumnum%2==1)
            return false;
        int half = sumnum/2;
        //dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少
        int dp[][] = new int[nums.length][half+1];
        dp[0][0] = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < half+1; i++) {
            if(nums[0]<=i)  dp[0][i] = nums[0];
            else dp[0][i] = 0;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < half+1; j++) {
                if(j>nums[i])
                    dp[i][j] = Math.max(dp[i-1][j], nums[i]+dp[i-1][j-nums[i]]);
                else dp[i][j] = dp[i-1][j];
            }
        }

        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < half+1; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }

        return dp[nums.length-1][half]==half;
    }




}
