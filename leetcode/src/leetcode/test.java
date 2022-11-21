package leetcode;

import TreeNode_pack.TreeNodeSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class test {
    public static void main(String[] args) {

        //输入字符串的
//        Scanner sc = new Scanner(System.in);
//        System.out.println("input:");
//        String input1 = sc.nextLine();
//        System.out.println(prac.reverseWords(input1));
//        System.out.println(prac.lengthOfLongestSubstring(input1));
//        prac.permutation(input1);
//////////        while (true){
//        System.out.println("input:");
////        String input1 = sc.nextLine();
//        double intinput1 = sc.nextDouble();
//        int intinput2 = sc.nextInt();
//        System.out.println(prac.myPow(intinput1,intinput2));
//        prac.reverseStr(input1,intinput1);
//        simple_string.isValid(input1);
//        System.out.println(simple_string.removeKdigits(input1,intinput1));
//        System.out.println("input:");
//        String input2 = sc.nextLine();
//        dymanic_process.minDistance2(input1,input2);
//        simple_string.addStrings(input1,input2);
//        solution.reverseVowels(input1);
//        System.out.println(solution.partition(input1));


        //输入list

        //二维数组输入
//        System.out.println("please enter the row and column:");
//        System.out.println("row number:");
//        int intinput1 = sc.nextInt();
//        System.out.println("column number:");
//        int intinput2 = sc.nextInt();
////        char[][] metrix = new char[intinput1][intinput2];
//        int[][] matrix = new int[intinput1][intinput2];
//        for (int j = 0; j < intinput1; j++) {
//            System.out.println("now please enter each String change to array, and use ‘,’ to split each element:");
//            String input = sc.next().toString();
//            String[] stringArr = input.split(",");
//            for (int k = 0; k < intinput2; k++) {
//                matrix[j][k] = Integer.parseInt(stringArr[k]);
//            }
//        }
//        prac.spiralOrder(matrix);

//        System.out.println("input string:");
//        prac.exist(metrix,input1);
        //cursion.numIslands(metrix);


        //输入int数组的，请以逗号隔开
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter a String change to array, and use ‘,’ to split each element:");
        String input = sc.next();
        String[] stringArr = input.split(",");
        int intArr[] = new int[stringArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(stringArr[i]);
        }
//        System.out.println("input Integer:");
        int intinput = sc.nextInt();
        prac.maxSlidingWindow(intArr,intinput);
//        System.out.println(prac.reverseWords(input));
//        System.out.println(prac.minNumber(intArr));
//        System.out.println(prac.maxSubArray(intArr));
//        System.out.println(prac.verifyPostorder(intArr));
////        System.out.println("input Integer:");
////        int intinput = sc.nextInt();
////        System.out.println(Arrays.toString(simple_string.nextGreaterElements(intArr)));
////        System.out.println(simple_string.largestRectangleArea(intArr));
//
//        System.out.println("please enter another String change to array, and use ‘,’ to split each element:");
//        String input2 = sc.next();
//        stringArr = input2.split(",");
//        int intArr2[] = new int[stringArr.length];
//        for (int i = 0; i < intArr2.length; i++) {
//            intArr2[i] = Integer.parseInt(stringArr[i]);
//        }
//        TreeNodeSolution.preOrderTraverse(TreeNodeSolution.buildTree(intArr,intArr2));
//        System.out.println(simple_string.maxNumber(intArr,intArr2,intinput));
//        System.out.println(Arrays.toString(intArr));
//        System.out.println(Arrays.toString(intArr2));
//        dymanic_process.maxUncrossedLines(intArr,intArr2);
//        dymanic_process.lengthOfLIS(intArr);

        //输入char数组的，请以逗号隔开
//         Scanner sc = new Scanner(System.in);
//         System.out.println("please enter a String change to array, and use ‘,’ to split each element:");
//         String input = sc.next().toString();
//         String[] stringArr = input.split(",");
//         char charArr[] = new char[stringArr.length];
//        for (int i = 0; i < charArr.length; i++) {
//            charArr[i] = stringArr[i].charAt(0);
//        }
//        solution.reverseString(charArr);

//        dymanic_process.rob2(intArr);
//        int intinput = sc.nextInt();
//        dymanic_process.numSquares(intinput);
//        cursion.canPartition(intArr);
//        System.out.println(dymanic_process.lastStoneWeightII(intArr));
//        solution.subsetsWithDup(intArr);
//        System.out.println(solution.permuteUnique(intArr).toString());
//        boolean res = solution.canJump(intArr);
//        System.out.println(res);
//        solution.moveZeroes(intArr);
//        System.out.println(solution.maxSubArray(intArr));
//        System.out.println(solution.summaryRanges(intArr1));
//        System.out.println(simple_array.majorityElement(intArr1));
//        System.out.println("input:");
//        String input2 = sc.nextLine();
//        int[] intArr2 = new int[input2.length()];
//        for (int i = 0; i < intArr2.length; i++) {
//            intArr2[i] = (int)input2.charAt(i) - 48;
//        }
//
//        solution.merge(intArr1,intArr1.length-intArr2.length,intArr2,intArr2.length);

        //输入整数的
//        Scanner sc = new Scanner(System.in);
//        System.out.println("input Integer:");
//        int intinput = sc.nextInt();
//        System.out.println(prac.translateNum(intinput));
//        System.out.println(prac.nthUglyNumber(intinput));
//        simple_int.arrangeCoins(intinput);
//        dymanic_process.maxProfit5(intArr,intinput);
//        cursion.numTrees(intinput);
//        int intinput2 = sc.nextInt();
//        solution.combine(intinput,intinput2);

        //输入字符串集合/数组的
//        Scanner sc = new Scanner(System.in);
//        System.out.println("please input string array's length:");
//        int strarraylen = sc.nextInt();
//        String[] strs = new String[strarraylen];
//        System.out.println("please input string array");
//        for (int i = 0; i < strarraylen; i++) {
//            strs[i] = sc.next().toString();
//        }
//        simple_string.evalRPN(strs);
//        System.out.println("please enter m and n!");
//        int intinput = sc.nextInt();
//        int intinput2 = sc.nextInt();
//        System.out.println(Arrays.toString(strs));
//        System.out.println(dymanic_process.findMaxForm(strs,intinput,intinput2));


//        String string = sc.nextLine();
//        System.out.println("input Integer:");
//        int intinput = sc.nextInt();
////      int intinput2 = sc.nextInt();
////      solution.combine(intinput,intinput2);
//        System.out.println("please enter strings here:");
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < intinput; i++) {
//            list.add(sc.next().toString());
//        }
//
//        System.out.println(list.toString());
//        System.out.println(cursion.wordBreak(string,list));

//        solution.permuteUnique(intArr);
        //System.out.println(solution.isPowerOfThree(intinput));
//        System.out.println("input Integer:");
//        int intinput2 = sc.nextInt();
//        int res = solution.uniquePaths(intinput,intinput2);

        //输入二维数组
    }
}

