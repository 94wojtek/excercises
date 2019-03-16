package collections;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WrongNamesCollectionTest {
    private NamesCollection names;
    private String wrongName;

    @Before
    public void setUp() {
        names = new NamesCollection();
        wrongName = "W@jtek";
    }

    @Test
    public void checkIfStringContainsSpecialChar() {
        assertTrue(names.charFinder(wrongName));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSpecialCharInput() {
        try {
            names.addName(wrongName);
            fail("Exception was not thrown.");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Incorrect character.", e.getMessage());
        }
    }
}
