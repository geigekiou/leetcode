package filetest;

import java.io.*;

public class filecopy {
    public static void main(String[] args) throws Exception {
        Reader rd = new FileReader("src/filetest/data4.txt");
        BufferedReader bfrd = new BufferedReader(rd);
        Writer wt = new FileWriter("src/filetest/data5.txt");
        BufferedWriter bfwt = new BufferedWriter(wt);
        String tempString = null;
        int line =0 ;
        while( (tempString = bfrd.readLine()) !=null){
            line++;
            bfwt.write(tempString + "/r/n");
        }
    }
}
