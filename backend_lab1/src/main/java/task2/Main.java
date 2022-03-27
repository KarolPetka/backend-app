package task2;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String text = "The quick\n" +
                "brown fox jumps\n" +
                "over the lazy\n" +
                "dog";
        try (FileOutputStream fo = new FileOutputStream("Task_2_File.txt")) {
            byte[] b = text.getBytes();
            fo.write(b);
        }
    }
}
