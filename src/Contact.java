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

	}

	public static void LoadList(List<String> stringList) {
		System.out.println();
		System.out.println("Name  |   Phone Number");
		System.out.println("----------------------");

		for(int i = 0; i < stringList.size(); i += 2){
       System.out.println(stringList.get(i) + " | " + stringList.get(i+1));
    	}
		System.out.println();
	}

	public static void addContact(Path path) throws IOException {
		Input input = new Input();

		System.out.println("--- Add a Contact ---");
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
		LoadList(printList);
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
			System.out.printf("%nContact %s not found%n", userSearch);
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
		runContactManager();
	}

	public static void runContactManager() throws IOException {

		Input input = new Input();
		String directory = "./src/data";
		String fileName = "contacts.txt";
		Path dataFile = Paths.get(directory, fileName);

		boolean confirmation = true;

		do {
			List<String> printList = Files.readAllLines(dataFile);
			LoadList(printList);
			System.out.printf("1. View contacts.\n2. Add a new contact.\n3. Search a contact by name.\n4. Delete an existing contact.\n5. Exit.\nEnter an option (1, 2, 3, 4 or 5):%n");
			String userSelection = input.getString();
			System.out.println("userSelection = " + userSelection);

			switch (userSelection) {
				case "1" -> runContactManager();
				case "2" -> addContact(dataFile);
				case "3" -> searchContact(dataFile, input);
				case "4" -> deleteContact(dataFile, input);
				case "5" -> confirmation = false;
				default -> runContactManager();
			}
		} while (confirmation);

	}

	public static void main(String[] args) throws IOException {
		runContactManager();
	}
}
