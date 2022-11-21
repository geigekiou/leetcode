package filetest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class filetrans {
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
