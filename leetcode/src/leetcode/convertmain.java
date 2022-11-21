package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class convertmain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("String:");
        String s = scanner.nextLine();
        System.out.println("Int:");
        int numRows = scanner.nextInt();
        // 分为 n组 n-2组
        List<String> nlist = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        if(numRows == 1){
            System.out.println(s);
        }

        int turnnum = s.length()/(2*numRows-2);
        int remainnum = s.length()%(2*numRows-2);
        int j=0;
        for (int i = 0; i < turnnum; i++) {
            nlist.add(s.substring(j,j+2*numRows-2));
            j = j+2*numRows-2;
        }

        if(remainnum!=0) {
            StringBuilder duiqi = new StringBuilder(s.substring(s.length() - remainnum));
            while(duiqi.length()<2*numRows-2){
                duiqi.append('*');
            }
            nlist.add(duiqi.toString());
        }

        for (int i = 0; i < nlist.size(); i++) {
            sb.append(nlist.get(i).charAt(0));
        }
        for (int k = 1; k <= numRows-2; k++) {
            for (int i = 0; i < nlist.size(); i++) {
                sb.append(nlist.get(i).charAt(k));
                sb.append(nlist.get(i).charAt(nlist.get(i).length()-k));
            }
        }
        for (int i = 0; i < nlist.size(); i++) {
            sb.append(nlist.get(i).charAt(numRows-1));
        }

        for (int i = sb.length()-1; i >= 0; i--) {
            if(sb.charAt(i) == '*'){
                sb.deleteCharAt(i);
            }
        }
        System.out.println(sb.toString());
//
//        for (int i = 1; i < numRows-2; i++) {
//            for (int k = 0; k < turnnum + (remainnum==0?0:1); k++) {
//                try{
//                    sb.append(nlist.get(k).charAt(i));
//                }
//                catch (Exception e){
//                    sb.append("");
//                }
//
//                try{
//                    sb.append(n_2list.get(k).charAt(n_2list.get(k).length()-i));
//                }
//                catch (Exception e){
//                    sb.append("");
//                }
//            }
//            }
        }

        //这里想出来一个很好的思路——对齐！
//        System.out.println(nlist.toString());
//        System.out.println(n_2list.toString());
    }

