package task1;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "simple.txt";

        try (FileInputStream fis = new FileInputStream(fileName)) {

            int i;

            while ((i = fis.read()) != -1) {
                System.out.print((char) i);
            }
        }
    }
}
