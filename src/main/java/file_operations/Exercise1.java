package file_operations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Choose file path to write data: ");
        Scanner input = new Scanner(System.in);
        String path = input.next();
        FileWriter fileWriter = new FileWriter(path);
        String data = " ";

        do {
            System.out.println("Type data or [-] to exit: ");
            data = input.next();
            fileWriter.write(data + System.lineSeparator());
        }
        while (!data.equals("-"));

        input.close();
        fileWriter.close();
    }
}
