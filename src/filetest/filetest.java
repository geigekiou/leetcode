package filetest;

import java.io.*;
import java.text.SimpleDateFormat;

public class filetest {
    public static void main(String[] args) throws Exception {
        Reader rder = new FileReader("src/filetest/data3.txt");
        BufferedReader brer = new BufferedReader(rder);
        String tempString = null;
        int line = 1;
        while ((tempString = brer.readLine()) != null) {
            System.out.println("line " + line + ": " + tempString);
            line++;
        }
    }
}
