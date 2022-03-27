package extra1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int counter = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);

        Iterator<Integer> arrayListIterator = arrayList.iterator();
        while (arrayListIterator.hasNext()) {
            int x = arrayListIterator.next();
            if (counter % 2 != 0) {
                arrayListIterator.remove();
            }
            counter++;
        }

        counter = 0;

        Iterator<Integer> linkedListIterator = linkedList.iterator();
        while (linkedListIterator.hasNext()) {
            int x = linkedListIterator.next();
            if (counter % 2 != 0) {
                linkedListIterator.remove();
            }
            counter++;
        }

        System.out.println("ArrayList elements: " + arrayList);
        System.out.println("LinkedList elements: " + linkedList);
    }
}
