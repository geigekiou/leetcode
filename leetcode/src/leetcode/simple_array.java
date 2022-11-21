package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class simple_array {
    public static int[] function(int[] digits) {
        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1]++;
            return digits;
        }
        else {
            digits[digits.length - 1] = 0;
            for (int i = digits.length - 2; i >= 0; i--) {
                if (digits[i] == 9) {
                    digits[i] = 0;
                } else {
                    digits[i]++;
                    break;
                }
            }
            if (digits[0] == 0) {
                int[] returnarr = new int[digits.length + 1];
                returnarr[0] = 1;
                for (int i = 1; i < returnarr.length; i++) {
                    returnarr[i] = 0;
                }
                return returnarr;
            }
        }
        return digits;
    }

    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        return nums[nums.length/2];
    }
}