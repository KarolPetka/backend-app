package task6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("simple.txt"));

        while (input.hasNextLine())
        {
            System.out.println(input.nextLine());
        }
    }
}
