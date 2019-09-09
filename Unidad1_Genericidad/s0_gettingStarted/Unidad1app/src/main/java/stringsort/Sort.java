package stringsort;

public class Sort {
    // static int n;

    /* static {
        n = 0;
    } */

    public static void sort(String[] names)
    {
        // System.out.println(n);

        // n = names.length;
        var n = names.length;

        while (n > 1)
        {
            for (var j = 0; j < n -1; j++) {
                if (names[j].compareTo(names[j+1]) > 0) {
                    final var tmp = names[j + 1];
                    names[j + 1] = names[j];
                    names[j] = tmp;
                }
                // System.out.println(tmp); // Error
            }
            n--;
        }
    }
}
