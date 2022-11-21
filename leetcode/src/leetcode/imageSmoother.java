package leetcode;

import java.util.ArrayList;
import java.util.Scanner;

public class imageSmoother {
    public static void main(String[] args) {
        //ArrayList<ArrayList<Integer>> imgmatrix = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("please input rows of the matrix:");
        int row = sc.nextInt();
        System.out.println("please input column of the matrix:");
        int column = sc.nextInt();
        int[][] imgMatrix = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.println("now you are input NO."+j+" of row "+i);
                imgMatrix[i][j] = sc.nextInt();
            }
        }

        int[][] resimgMatrix = solution.imageSmoother(imgMatrix);

        for (int i = 0; i < row; i++) {
            System.out.print("【");
            for (int j = 0; j < column; j++) {
                System.out.print(resimgMatrix[i][j]+" ");
            }
            System.out.println("】");
        }

    }
}