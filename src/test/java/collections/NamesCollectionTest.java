package collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NamesCollectionTest {
    private NamesCollection names1;
    private NamesCollection names2;
    private NamesCollection names3;

    @Before
    public void setUp() {
        names1 = new NamesCollection();

        names1.getNames().add("Wojtek");
        names1.getNames().add("Ola");
        names1.getNames().add("Ela");
        names1.getNames().add("Ela");
        names1.getNames().add("Ola");
        names1.getNames().add("Ela");
        names2 = names1;
    }

    @Test
    public void shouldAddNameToList() {
        names3 = new NamesCollection();
        names3.addName("Wojtek");
        assertTrue(names3.getNames().contains("Wojtek"));
    }

    @Test
    public void shouldDisplayNumberOfUniqueNames() {
        assertEquals(3, names1.convertListToSet());
    }

    @Test
    public void shouldCheckIfEquals() {
        assertEquals(names2, names1);
    }

    @Test
    public void shouldCheckIfSameHashCode() {
        assertEquals(names2.hashCode(), names1.hashCode());
    }

    @Test
    public void shouldDisplayCorrectly() {
        String representationSample = "Wojtek" + System.lineSeparator() +
                "Ola" + System.lineSeparator() +
                "Ela" + System.lineSeparator() +
                "Ela" + System.lineSeparator() +
                "Ola" + System.lineSeparator() +
                "Ela" + System.lineSeparator();
        assertEquals(representationSample, names1.toString());
    }
}