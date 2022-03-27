package extra3;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String text = "The quick\n" +
                "brown fox jumps\n" +
                "over the lazy\n" +
                "dog";

        HashMap<Character, Integer> rowCountMap = new HashMap<>();

        char[] textArray = text.toCharArray();

        for (char c : textArray) {
            if (c == '\n') {
                if (rowCountMap.containsKey(c)) {
                    rowCountMap.put('\n', rowCountMap.get(c) + 1);
                } else {
                    rowCountMap.put('\n', 1);
                }
            }
        }
        System.out.println(rowCountMap);
    }
}
