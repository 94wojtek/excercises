package nested.classes;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;
import static org.junit.Assert.*;

public class WordSortTest {
    private WordSort wordSort;

    @Before
    public void setUp() {
        wordSort = new WordSort();
    }

    @Test
    public void shouldCheckIfEquals() {
        assertEquals(0, wordSort.comparator.compare("hej", "hej"));
    }

    @Test
    public void shouldCheckIfAddWordsToList() {
        wordSort.addWords("michaubiauek");
        assertTrue(wordSort.getWords().contains("michaubiauek"));
    }

    @Test(expected = InputMismatchException.class)
    public void shouldThrowInputMismatchExceptionWhenWordContainsSpecChar() {
        wordSort.addWords("i<3mirko");
    }

    @Test
    public void shouldDisplayCorrectly() {
        wordSort.getWords().add("mirko");
        wordSort.getWords().add("kosmonauta");
        wordSort.getWords().add("serwerownia");
        wordSort.getWords().add("rogale");

        String sample = "mirko" + System.lineSeparator() +
                        "kosmonauta" + System.lineSeparator() +
                        "serwerownia" + System.lineSeparator() +
                        "rogale" + System.lineSeparator();

        assertEquals(sample, wordSort.toString());
    }
}