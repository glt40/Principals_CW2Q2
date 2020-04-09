import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quicksort {

    // Initialise fields
    private List<String> lst_names;
    private String filename;

    /**
     * Constructor to assign values
     */
    public Quicksort() {
        lst_names = new ArrayList<String>();
        filename = "names2.txt";
    }

    /**
     * Puts some values in the List, either from a file of Strings separated by spaces, or a few hardcoded names
     * If reading the file throws an exception, those hardcoded names are added. Give all strings in UPPERCASE
     */
    private void lst_populate() {
        // Open up the file for reading
        try {
            Scanner s = new Scanner(new File(filename));
            while (s.hasNext()){
                lst_names.add(s.next());
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, adding temporary values instead.");
            lst_names.add("GEMMA");
            lst_names.add("GEM");
            lst_names.add("WILL");
            lst_names.add("FIONA");
            lst_names.add("JILL");
            lst_names.add("JUSTIN");
            lst_names.add("MAEVA");
            lst_names.add("JESS");
            lst_names.add("LEONIE");
            lst_names.add("MARC");
            lst_names.add("ANDREW");
        }
    }

    /**
     * Prints out each Object in a List
     */
    private void lst_print() {
        for (Object lst_name : lst_names) {
            System.out.println(lst_name);
        }
    }

    /**
     * You can compare strings by calling string.compareTo(string2)
     * However, in the interests of re-implementation of our own methods, I have used my own compare function
     * It returns -1 if string1 comes alphabetically before string2, 1 for after and 0 if they are the same
     */
    private int lst_compare(String string1, String string2) {
        // If first chars are equal, skip to recursion
        char fchar1 = 0;
        char fchar2;
        try {
            fchar1 = string1.charAt(0);
            fchar2 = string2.charAt(0);
        } catch (Exception e) {
            // Check for null char. If char was NULL, exception was thrown
            if (fchar1 == '\u0000') {
                // string 1 goes alph. first
                return -1;
            }
            else return 1;
        }
        if (fchar1 == fchar2) {
            // Recurse, removing the first character from each string, as we know they are identical
            return lst_compare(string1.substring(1), string2.substring(1));
        } else {
            // Chars are not equal so return a result after casting chars to ints to compare
            if((int)fchar1 > (int)fchar2) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * You can swap elements in array list by calling Collections.swap(list, i, j)
     * However, in the interests of re-implementation of our own methods, I have used my own swap function
     */
    private void lst_swap_el(List<String> list, int i, int j) {
        String tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    /**
     * The quicksort works with a "divide and conquer" method and calls itself recursively
     */
    private void lst_quicksort(List<String> list, int start, int end) {
        int i = start;
        int j = end;
        // Only try to sort a list if it's larger than one element
        if (j - i >= 1) {
            String pivot = list.get(i);
            while (j > i) {
                while (lst_compare(list.get(i), pivot) <= 0 && i < end && j > i) {
                    i++;
                }
                while (lst_compare(list.get(j), pivot) >= 0 && j > start && j >= i) {
                    j--;
                }
                if (j > i)
                    lst_swap_el(list, i, j);
            }
            lst_swap_el(list, start, j);
            // Call recursively for the two halves of the list
            lst_quicksort(list, start, j - 1);
            lst_quicksort(list, j + 1, end);
        }
    }

    /**
     * Run is the only method called by main
     * It calls the function to initialise list from file, the function to sort list and the function to print list
     */
    private void run() {
        // Populate lst_names
        lst_populate();
        lst_print();
        System.out.println();
        // Sort lst_names with quicksort
        lst_quicksort(lst_names,0,(lst_names.size()-1));
        lst_print();
    }

    /**
     * Main method creates a new Quicksort object and calls the run() method
     */
    public static void main(String[] args) {
        Quicksort sort = new Quicksort();
        sort.run();
    }
}
