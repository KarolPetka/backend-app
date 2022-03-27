package extra2;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[] array = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};

        HashSet<Integer> hashSet = new HashSet<>();

        for (int i : array) hashSet.add(i);

        System.out.print(hashSet);
    }
}
