package leetcode;

import java.util.List;

public class mytool {
    public static void array2list(List<Integer> reslist, int[] arr){
        for (int i = 0; i < arr.length; i++) {
            reslist.add(arr[i]);
        }
    }
}
