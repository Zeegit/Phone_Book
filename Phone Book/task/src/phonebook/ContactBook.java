package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class ContactBook {
    ArrayList<Contact> list;
    Contact[] listArray;
    Hashtable<String,String> hashtable;

    public ContactBook() {
        list = new ArrayList<>();
    }

    void loadFromFile(String fileName) {
        File file = new File(fileName);
        try (Scanner scn = new Scanner(file)) {
            while (scn.hasNext()) {
                String[] a = scn.nextLine().trim().split("\\s+",2);
                list.add(new Contact(a[0], a[1]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        listArray = new Contact[list.size()];
        for(int i = 0; i < list.size(); i++) {
            listArray[i] = new  Contact(list.get(i).getPhone(), list.get(i).getName());
        }

    }

    public List<String> find(String s) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(s)) {
            //if (list.get(i).getName().toUpperCase().equals(s.toUpperCase())) {
                result.add(list.get(i).getName());

                break;
            }
        }
        return result;
    }

    public List<String> findArray(String s) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < listArray.length; i++) {
            if (listArray[i].getName().toUpperCase().equals(s.toUpperCase())) {
                result.add(listArray[i].getName());
                break;
            }
        }
        return result;
    }

    public boolean bubbleSort(long workTime) {
        long timeStart = System.currentTimeMillis();
        Contact temp;
        for (int i = 0; i < list.size() - 1; i++) {

            if (System.currentTimeMillis() - timeStart > 10 * workTime) {
                return true;
            }
            // System.out.println((double)i/list.size()*100);
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getName().compareTo(list.get(j + 1).getName()) > 0) {
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                }
            }
        }
        return false;
    }


    public boolean quickSort(long workTime) {
        long timeStart = System.currentTimeMillis();
        quickSort2(0, list.size()-1);

        return false;
    }

    public void quickSort2(int leftBorder, int rightBorder) {
        // list.get(j).getName().compareTo(list.get(j + 1).getName()) > 0)
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        String pivot = list.get((leftMarker + rightMarker) / 2).getName();
        //int pivot = source[pivotIndex];
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (list.get(leftMarker).getName().compareTo(pivot)  < 0) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (list.get(rightMarker).getName().compareTo(pivot)  > 0) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {

                    Contact temp = list.get(leftMarker);
                    list.set(leftMarker, list.get(rightMarker));
                    list.set(rightMarker, temp);

                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quickSort2(leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort2(leftBorder, rightMarker);
        }
    }

    public void bubbleSort2() {
        boolean swapped;
        Contact temp;
        for (int i = 0; i < listArray.length - 1; i++) {
            swapped = false;
            System.out.println((double)i/listArray.length*100);
            for (int j = 0; j < listArray.length - i - 1; j++) {
                if (listArray[j].getName().compareTo(listArray[j + 1].getName()) > 0) {
                    temp = listArray[j];
                    listArray[j] = listArray[j + 1];
                    listArray[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public List<String> findBinarySerch(String s) {
        List<String> result = new ArrayList<>();
        int m = binarySearch(s, 0, list.size());
        if (m != -1) {
            result.add(list.get(m).getName());
            //System.out.println(list.get(m).getPhone() + " " + list.get(m).getName());
        }
        return result;
    }

    public int binarySearch(String elem, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2; // the index of the middle element

            if (elem.equals(list.get(mid).getName())) {
                return mid; // the element is found, return its index
            } else if (list.get(mid).getName().compareTo(elem)  > 0) {
                right = mid - 1; // go to the left subarray
            } else {
                left = mid + 1;  // go the the right subarray
            }
        }
        return -1; // the element is not found
    }

    public void createHashTable() {
        hashtable = new Hashtable<>();

        for (Contact c: list) {
            hashtable.put(c.getName(), c.getPhone());
        }
    }

    public List<String> findHashTable(String s) {
        List<String> result = new ArrayList<>();

        if (hashtable.containsKey(s)) {
            result.add(hashtable.get(s));
        }
        return result;
    }
}
