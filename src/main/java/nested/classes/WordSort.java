package nested.classes;

import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSort {
    private List<String> words;
    final Pattern p = Pattern.compile("\\W|\\d");

    public WordSort() {
        this.words = new LinkedList<>();
    }

    public List<String> getWords() {
        return words;
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), words);
    }

    public void addWords(String word) {
        Matcher m = p.matcher(word);
        if(m.find()) {
            throw new InputMismatchException("Only letters allowed.");
        }
        words.add(word);
    }

    static Comparator<String> lambdaComparator = Comparator.comparingInt(String::length);

    /*
    static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String word1, String word2) {
            return Integer.compare(word1.length(), word2.length());
        }
    };
    */
}

class WordSortMain {
    public static void main(String[] args) {
        WordSort wordSort = new WordSort();
        String word;

        try(Scanner input = new Scanner(System.in)) {
            for (int counter = 1; counter < 6; counter++) {
                while(true) {
                    System.out.println("Type word number " + counter + ": ");
                    try {
                        word = input.next();
                        wordSort.addWords(word);
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                }
            }
        }

        System.out.println("Before sort: \n" + wordSort.toString());
        Collections.sort(wordSort.getWords(), WordSort.lambdaComparator);
        System.out.println("After sort: \n" + wordSort.toString());
    }
}