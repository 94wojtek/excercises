package collections;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamesCollection {
    private List<String> names;
    private static final Pattern p = Pattern.compile("\\W|\\d");

    public NamesCollection() {
        this.names = new LinkedList<>();
    }

    public List<String> getNames() {
        return names;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NamesCollection)) {
            return false;
        }
        NamesCollection that = (NamesCollection) o;
        return Objects.equals(names, that.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }

    @Override
    public String toString() {
        StringBuilder namesRep = new StringBuilder();
        for(String name : names) {
            namesRep.append(name);
            namesRep.append(System.lineSeparator());
        }

        return namesRep.toString();
    }

    public void addName(String name) {
        if (charFinder(name)) {
            throw new IllegalArgumentException("Incorrect character.");
        }

        names.add(name);
    }

    //convert List to Set to retrieve unique names
    public int convertListToSet() {
        Set<String> convertedNames = new LinkedHashSet(names);
        return convertedNames.size();
    }

    //method checks if input String contains chars other than letters
    public boolean charFinder(String inputString) {
        Matcher m = p.matcher(inputString);

        return m.find();
    }
}

class Whatever {
    public static void main(String[] args) {
        NamesCollection names = new NamesCollection();
        System.out.println("Type name you wish to add or [-] to finish: ");
        Scanner input = new Scanner(System.in);
        String name;

        try {
            while (true) {
                name = input.next();
                if (name.equals("-")) {
                    break;
                }
                names.addName(name);
            }
            input.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            while (true) {
                try {
                    name = input.next();
                    if (name.equals("-")) {
                        break;
                    }
                    names.addName(name);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            input.close();
        } finally {
            System.out.println(names.toString());
            System.out.println("Number of unique names in the list: " + names.convertListToSet());
        }
    }
}
