package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class greedy {
    public static int findContentChildren(int[] g, int[] s) {
        if(s.length==0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);

        int iterator = 0;
        int begin = 0;
        int ans = 0;

        for (int i = 0; i < g.length; i++) {
            iterator = g[i];
            for (int j = begin; j < s.length; j++) {
                if(s[j] >= iterator) {
                    ans++;
                    begin = j+1;
                    break;
                }
            }
        }

        return ans;
    }

    public static int wiggleMaxLength(int[] nums) {
        int ans = 0, temp;

        if(nums.length==1) return 1;
        if(nums.length==2) {
            if (nums[1] - nums[0] != 0) return 2;
            else return 0;
        }

        temp= nums[1]-nums[0];
        if(temp!=0) ans=2;
        else ans = 1;

        for (int i = 2; i < nums.length; i++) { //[3,3,3,2,5]
            if(nums[i] == nums[i-1]) continue;
            if((nums[i]-nums[i-1])*temp<0) {
                ans++;
            }
            temp = nums[i]-nums[i-1];
        }
        return ans;
    }
}
