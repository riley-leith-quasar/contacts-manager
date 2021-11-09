import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contact {
    public String name;
    public String number;

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }

    public static void LoadList(Path path) throws IOException {
        System.out.println("Name  |   Phone Number");
        System.out.println("----------------------");



        List<String> printList = Files.readAllLines(path);
        for(int i = 0; i < printList.size(); i++){
            System.out.println((i + 1) + ": " + printList.get(i));
        }

        printList = Files.readAllLines(path);
        List<String> newList = new ArrayList<>();

        for(String line: printList){
            newList.add(line);
        }
        System.out.println(newList);
        Files.write(path, newList);
    }

    public static void addContact() throws IOException {
        String directory = "./src/data";
        String fileName = "contacts.txt";
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, fileName);

        Contact test = new Contact("James", "5555555");
//        List<Contact> contactList = Arrays.asList(test);
        List<Contact> contactList1 = new ArrayList<>();
        contactList1.add(test);
        System.out.println("contactList = " + contactList1);

//        Files.write(dataFile, contactList1);

        //Reading from the file
        List<String> printList = Files.readAllLines(dataFile);
        System.out.println("printList = " + printList);

        //We can write our own print method
        for(int i = 0; i < printList.size(); i++){
            System.out.println((i + 1) + ": " + printList.get(i));
        }

//        ArrayList<Contact> contactList = new ArrayList<>();
//        List<String> contactList1 = contactList.
//        contactList.add(new Contact("James", "555-5555"));
//        for(Contact contact: contactList){
//            System.out.println(contact);
//        }

//        Files.write(dataFile, (Iterable<? extends CharSequence>) contactList);
//        List<Contact> contactList = List.of(test);
//        Files.write(path, contactList);
//        System.out.println(contactList);
    }

    public static void main(String[] args) throws IOException {
        addContact();
    }
}
