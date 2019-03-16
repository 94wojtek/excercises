package file_operations;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) throws IOException {
        System.out.println("Type file path to download data: ");
        Scanner input = new Scanner(System.in);
        String path = input.next();
        DataInputStream inputStream = new DataInputStream(new FileInputStream(path));
        int bDay;
        int bMonth;
        int bYear;

        try {
           bDay = inputStream.readInt();
           bMonth = inputStream.readInt();
           bYear = inputStream.readInt();
        }
        finally {
            input.close();
            inputStream.close();
        }

        System.out.println(bDay + "/" + bMonth + "/" + bYear);
    }
}
