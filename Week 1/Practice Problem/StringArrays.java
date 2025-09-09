public class StringArrays {

    public static String findLongestName(String[] names) {
        String longest = names[0];
        for (String name : names) {
            if (name.length() > longest.length()) {
                longest = name;
            }
        }
        return longest;
    }

    public static int countNamesStartingWith(String[] names, char letter) {
        int count = 0;
        for (String name : names) {
            if (name.toLowerCase().charAt(0) == Character.toLowerCase(letter)) {
                count++;
            }
        }
        return count;
    }

    public static String[] formatNames(String[] names) {
        for (int i = 0; i < names.length; i++) {
            String[] parts = names[i].split(" ");
            names[i] = parts[parts.length - 1] + ", " + parts[0];
        }
        return names;
    }

    public static void main(String[] args) {
        String[] students = {
                "John Smith",
                "Alice Johnson",
                "Bob Brown",
                "Carol Davis",
                "David Wilson"
        };

        String longestName = findLongestName(students);
        int countA = countNamesStartingWith(students, 'A');
        String[] formattedNames = formatNames(students.clone());

        System.out.println("Longest name: " + longestName);
        System.out.println("Names starting with 'A': " + countA);
        System.out.println("Formatted names:");
        for (String name : formattedNames) {
            System.out.println(name);
        }
    }
}

