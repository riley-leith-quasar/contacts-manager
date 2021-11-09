import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

	public static ArrayList<Contact> createContactsArray(Path path) throws IOException {
		List<String> printList = Files.readAllLines(path);

		ArrayList<Contact> contactArr = new ArrayList<>();

		for(int i = 0; i < printList.size(); i += 2){
			contactArr.add(new Contact(printList.get(i), printList.get(i+1)));
		}

		return contactArr;

//		for (Contact contact : contactArr){
//			System.out.printf("%s | %s%n", contact.name, contact.number);
//		}
	}

	public static void LoadList(Path path, List<String> stringList) throws IOException {
		System.out.println("Name  |   Phone Number");
		System.out.println("----------------------");

//		for(int i = 0; i < arrayList.size(); i++){
//			System.out.printf("%s | %s", arrayList.get(i).name, arrayList.get(i).number);
//
//		}
		for(int i = 0; i < stringList.size(); i += 2){
       System.out.println(stringList.get(i) + " : " + stringList.get(i+1));
    	}
	}

	public static void addContact(Path path) throws IOException {
		Input input = new Input();

		System.out.println("Enter contact name: ");
		String contactName = input.getString();
		System.out.println("Enter your contact's phone number");
		String contactNumber = input.getString();


		ArrayList<Contact> contactArr = new ArrayList<>();
		contactArr.add(new Contact(contactName, contactNumber));
		for (Contact contact : contactArr) {
			List<String> contactList = Arrays.asList(contact.name, contact.number);
			System.out.println("contactList = " + contactList);
			Files.write(path, contactList, StandardOpenOption.APPEND);
		}
		List<String> printList = Files.readAllLines(path);
		LoadList(path, printList);


	}

	public static void searchContact(Path path, Input input) throws IOException {
		ArrayList<Contact> contactArr = createContactsArray(path);
		System.out.println("Enter contact name: ");
		String userSearch = input.getString();
		boolean found = false;

		for (Contact contact : contactArr) {
			if (userSearch.equalsIgnoreCase(contact.name)) {
				System.out.printf("%s | %s%n", contact.name, contact.number);
				found = true;
				break;
			}
		}
		if (!found){
			System.out.printf("Contact %s not found%n", userSearch);
			searchContact(path, input);
		}
	}

	public static void deleteContact(Path path, Input input) throws IOException {
		ArrayList<Contact> contactArr = createContactsArray(path);
		System.out.println("Enter a contact: ");
		String userSearch = input.getString();

		List<String> newList = new ArrayList<>();

		for (Contact contact : contactArr) {
			if (userSearch.equalsIgnoreCase(contact.name)) {
				continue;
			}
			newList.add(contact.name);
			newList.add(contact.number);
		}
		Files.write(path, newList);
	}

	public static void main(String[] args) throws IOException {
		Input input = new Input();
		String directory = "./src/data";
		String fileName = "contacts.txt";
		Path dataDirectory = Paths.get(directory);
		Path dataFile = Paths.get(directory, fileName);

		addContact(dataFile);
		System.out.println();
		searchContact(dataFile, input);
		deleteContact(dataFile, input);
	}
}
