package phonebook;

public class Main {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        ContactFinder f = new ContactFinder();
        f.findFromFile("c:\\zee\\find.txt", timeStart);
    }
}
