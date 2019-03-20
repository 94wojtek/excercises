package nested.classes;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSort {
    private List<String> words;

    public WordSort() {
        this.words = new LinkedList<>();
    }

    public List<String> getWords() {
        return words;
    }

    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder();
        for(String word : words) {
            representation.append(word);
            representation.append(System.lineSeparator());
        }
        return representation.toString();
    }

    public void addWords(String word) {
        final Pattern p = Pattern.compile("\\W|\\d");
        Matcher m = p.matcher(word);
        if(m.find()) {
            throw new InputMismatchException("Only letters allowed.");
        }
        words.add(word);
    }

    static Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String word1, String word2) {
            if (word1.length() > word2.length()) {
                return 1;
            }
            if (word1.length() < word2.length()) {
                return -1;
            }
            return 0;
        }
    };
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
        Collections.sort(wordSort.getWords(), WordSort.comparator);
        System.out.println("After sort: \n" + wordSort.toString());
    }
}