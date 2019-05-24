package lambda.expression;

import java.util.*;
import java.util.function.Consumer;

public class Exercise1 {
    public static void main(String[] args) {

        List<String> words = new LinkedList<>();
        Scanner input = new Scanner(System.in);
        String word;

        for(int counter = 1; counter < 5; counter++) {
            word = input.next();
            words.add(word);
        }

        System.out.println("Before sorting: ");
        System.out.println(words.toString());

        Comparator<String> comparator = (word1, word2) -> Integer.compare(word2.length(), word1.length());
        Consumer<Comparator> sorted = words::sort;
        sorted.accept(comparator);

        System.out.println("After sorting: ");
        System.out.println(words.toString());
    }
}
