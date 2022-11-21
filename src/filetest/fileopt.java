package filetest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class fileopt {
    public static void main(String[] args) throws Exception {
       OutputStream os = new FileOutputStream("src/filetest/data3.txt",true);
       os.write('a');
       os.write('b');
       byte[] buffer = "流".getBytes();
       os.write(buffer);
       os.write("\r\n".getBytes(StandardCharsets.UTF_8));
       os.write('b');
       os.flush();
       os.close();
//        byte[] buffers = new byte[3];
//        int len = 0;
//        byte[] buffer2 = is.readAllBytes();
//        String res = new String(buffer2);
//        System.out.println(res);
//            System.out.println(res);
//        while((len = is.read(buffers))!=-1)
//        {
//            String res = new String(buffers,0,len);
//            System.out.println(res);
//    }
    }
}
