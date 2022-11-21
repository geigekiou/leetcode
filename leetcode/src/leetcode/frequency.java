package leetcode;

import java.util.*;

public class frequency {
    public static String frequencySort(String s) {
        char[] sarray= s.toCharArray();
        Map<Character,Integer> mymap = new HashMap<>();

        for (int i = 0; i < sarray.length; i++) {
            if (mymap.containsKey(sarray[i])){
                mymap.put(sarray[i], mymap.get(sarray[i])+1);
            }
            else{
                mymap.put(sarray[i],1);
            }
        }

        List<Map.Entry<Character, Integer>> infoIds =
                new ArrayList<Map.Entry<Character, Integer>>(mymap.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infoIds.size(); i++) {
            for (int j = 0; j < infoIds.get(i).getValue(); j++) {
                sb.append(infoIds.get(i).getKey());
            }
        }

        return sb.toString();
    }
}

//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
//
//        示例 1:
//
//        输入:
//        "tree"
//
//        输出:
//        "eert"
//
//        解释:
//        'e'出现两次，'r'和't'都只出现一次。
//        因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。