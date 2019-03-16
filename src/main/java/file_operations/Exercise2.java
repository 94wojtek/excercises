package file_operations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Type file path to download data: ");
        Scanner input = new Scanner(System.in);
        String path = input.next();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        String output = " ";
        int counter;

        try {
            for (counter = 0; counter <= output.length(); counter++) {
                output = bufferedReader.readLine();
                System.out.println(output);
            }
            System.out.println("Number of lines in file: " + counter);
        }
        finally {
            input.close();
            bufferedReader.close();
        }
    }
}
