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
		for(int i = 0; i < contactArr.size(); i++){
			List<String> contactList = Arrays.asList(contactArr.get(i).name, contactArr.get(i).number);
			System.out.println("contactList = " + contactList);
			Files.write(path, contactList, StandardOpenOption.APPEND);
		}
		List<String> printList = Files.readAllLines(path);
		LoadList(path, printList);


	}

	public static void main(String[] args) throws IOException {
		String directory = "./src/data";
		String fileName = "contacts.txt";
		Path dataDirectory = Paths.get(directory);
		Path dataFile = Paths.get(directory, fileName);

		addContact(dataFile);
		System.out.println();
	}
}
