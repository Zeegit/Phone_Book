package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactFinder {
    ContactBook book;

    public ContactFinder() {
        init();
    }

    public void init() {
        book = new ContactBook();
        book.loadFromFile("c:\\zee\\directory.txt");
    }

    public void findFromFile(String fileName, long timeStart) {

        List<String> list = loadFromFile(fileName);
        System.out.println("Start searching (linear search)...");


        int findedContacts = 0;
        for (int i = 0; i < list.size(); i++) {
            List<String> f = book.find(list.get(i));
            if (f.size() > 0) {
                findedContacts++;
            }
        }

        long timeStart2 = System.currentTimeMillis();
        long workTime = timeStart2 - timeStart;
        System.out.println("Found " + findedContacts + " / " + list.size() + " entries. Time taken: " + time2Text(workTime));

        System.out.println();
        System.out.println("Start searching (bubble sort + jump search)...");
        //timeStart = System.currentTimeMillis();

        boolean isStop = book.bubbleSort(workTime);
        long sortingTime = System.currentTimeMillis() - timeStart2;

        findedContacts = 0;
        for (int i = 0; i < list.size(); i++) {
            List<String> f = book.find(list.get(i));
            if (f.size() > 0) {
                findedContacts++;
            }
        }

        long timeStart_QuickSort_BinarySearch = System.currentTimeMillis();
        long allTime = timeStart_QuickSort_BinarySearch - timeStart2;

        System.out.println("Found " + findedContacts + " / " + list.size() + " entries. Time taken: " + time2Text(allTime));
        System.out.println("Sorting time: " + time2Text(sortingTime) + (isStop?" - STOPPED, moved to linear search":""));
        System.out.println("Searching time: " + time2Text(allTime-sortingTime));

        //
        init();
        System.out.println();
        System.out.println("Start searching (quick sort + binary search)...");

        boolean isStop_QuickSort = book.quickSort(workTime);
        long sortingTime_QuickSort = System.currentTimeMillis() - timeStart_QuickSort_BinarySearch;

        findedContacts = 0;
        for (int i = 0; i < list.size(); i++) {
            List<String> f = book.findBinarySerch(list.get(i));
            if (f.size() > 0) {
                findedContacts++;
            }
        }

        long timeStart_HashTable = System.currentTimeMillis();
        long allTime_QuickSort_BinarySearch = timeStart_HashTable - timeStart_QuickSort_BinarySearch;

        System.out.println("Found " + findedContacts + " / " + list.size() + " entries. Time taken: " + time2Text(allTime_QuickSort_BinarySearch));
        System.out.println("Sorting time: " + time2Text(sortingTime_QuickSort) + (isStop_QuickSort?" - STOPPED, moved to linear search":""));
        System.out.println("Searching time: " + time2Text(allTime_QuickSort_BinarySearch-sortingTime_QuickSort));

        //
        init();
        System.out.println();
        System.out.println("Start searching (hash table)...");

        book.createHashTable();
        long creatingTime_HashTable = System.currentTimeMillis() - timeStart_HashTable;

        findedContacts = 0;
        for (int i = 0; i < list.size(); i++) {
            List<String> f = book.findHashTable(list.get(i));
            if (f.size() > 0) {
                findedContacts++;
            }
        }

        //long timeStart_HashTable = ;
        long allTime_HashTable = System.currentTimeMillis() - timeStart_HashTable;

        System.out.println("Found " + findedContacts + " / " + list.size() + " entries. Time taken: " + time2Text(allTime_HashTable));
        System.out.println("Creating time: " + time2Text(creatingTime_HashTable)); // + (isStop_QuickSort?" - STOPPED, moved to linear search":""));
        System.out.println("Searching time: " + time2Text(allTime_HashTable-creatingTime_HashTable));

    }

    String time2Text(long workTime) {
        long min = workTime / 60000L;
        long sec = (workTime - min * 60000L) / 1000L;
        long ms = workTime - min * 60000L - sec * 1000L;
        return min + " min. " + sec + " sec. " + ms + " ms.";
    }


    List<String> loadFromFile(String fileName) {
        List<String> list = new ArrayList();
        File file = new File(fileName);
        try (Scanner scn = new Scanner(file)) {
            while (scn.hasNext()) {
                list.add(scn.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return list;
    }
}
