package file_operations;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) throws IOException {
        System.out.println("Choose file path to write data: ");
        Scanner input = new Scanner(System.in);
        String path = input.next();
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(path));

        int dob;

        System.out.println("Type day of your birth: ");
        dob = input.nextInt();
        outputStream.writeInt(dob);
        System.out.println("Typew month of your birth: ");
        dob = input.nextInt();
        outputStream.writeInt(dob);
        System.out.println("Type year of your birth: ");
        dob = input.nextInt();
        outputStream.writeInt(dob);

        input.close();
        outputStream.close();
    }
}
