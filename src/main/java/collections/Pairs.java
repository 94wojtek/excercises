package collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pairs {
    public static void main(String[] args) {
        Map<String, String> pairs = new HashMap<>();
        Scanner input = new Scanner(System.in);
        String name1;
        String name2;

        do {
            System.out.println("Type 1st name of pair: ");
            name1 = input.next();
            System.out.println("Type 2nd name of pair: ");
            name2 = input.next();
            pairs.put(name1, name2);
        }
        while (!name1.equals("-") || !name2.equals("-"));

        for (Map.Entry name : pairs.entrySet()) {
            System.out.println(name.getKey() + " " + name.getValue());
        }

        System.out.println("Type one of previously typed name: ");
        name1 = input.next();

        if (pairs.containsKey(name1)) {
            System.out.println("Pair for " + name1 + " is: " + pairs.get(name1));
        }

        input.close();
    }
}
