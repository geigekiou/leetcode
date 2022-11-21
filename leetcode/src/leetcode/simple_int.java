package leetcode;

import java.util.*;

public class simple_int {
    public static int function(int num){
        for (int i = 0; i < num/2; i++) {
            if(i*i<num && (i+1)*(i+1)>num){
                return i;
            }
        }
        return 0;
}

    public static int reverseBits(int n) {  //1011 1101
        int len = 32 - Integer.toBinaryString(n).length();
        int carry = 0;
        int num = 0;
        while(n>0){
            carry = n%2;
            n = n>>1;
            num  = (num+carry)<<1;
        }

        System.out.println(Integer.toBinaryString(num>>1));
        return (num>>1)<<len;
    }

    public static boolean ishappy(int n){
        int sum = 0;
        while(sum!=1){
            while(n>0){
                sum += (n%10)*(n%10);
                n/=10;
            }
            if (sum == 1){
                return true;
            }
            else {
                n = sum;
                sum = 0;
            }
        }
        return false;
    }

    public static int reverse(int x){
        int reversenum=0,flag=0;
        ArrayList<Integer> numlist = new ArrayList<Integer>();
        if(x<0) flag = 1;
        x = Math.abs(x);
        while (x>0){
            numlist.add(x%10);
            x/=10;
        }

        try{
        for (int i = 0; i < numlist.size(); i++) {
            reversenum = Math.addExact(reversenum, (int) ((int)numlist.get(numlist.size()-1-i)*Math.pow(10,i)));
            //if(reversenum>2147483646) return 0;
        }
        }
        catch (Exception e){
            return 0;
        }
        return flag==1?(-1)*reversenum:reversenum;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        List<Integer> reslist = new ArrayList<>();
        Set<Integer> newset = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            newset.add(nums[i]);
        }
        for (int i = 1; i <= len; i++) {
            if(!newset.contains(i))
                reslist.add(i);
        }
        System.out.println(reslist.toString());
        return reslist;
    }

    public static int arrangeCoins(int n) {
        int x = 0, arrsum = 0;
        x = (int)(-1 + Math.sqrt(1+8*n))/2;
        System.out.println(x);
        return x;
        //  (1+x)x/2 <= n
        // x^2+x-2n <= 0
        // dta = 1+4*2n
        // -1+ sqrt(1+8n)  /2
    }
}
