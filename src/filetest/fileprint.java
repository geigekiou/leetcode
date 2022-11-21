package filetest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class fileprint {
    public static void main(String[] args) throws Exception {
        Files.deleteIfExists(Path.of("C:\\Users\\Administrator\\Desktop\\刘雨蒙电子资料\\file_test/qwe.txt"));
    }
}
