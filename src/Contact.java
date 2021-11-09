import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
		for(int i = 0; i < printList.size(); i += 2){
			System.out.println(printList.get(i) + " : " + printList.get(i+1));
		}
	}

	public static void addContact(Path path) throws IOException {
		Input input = new Input();

		System.out.println("Enter contact name: ");
		String contactName = input.getString();

		Contact test = new Contact("Test", "5555555");

		List<String> contactList = Arrays.asList(test.name, test.number);
		System.out.println("contactList = " + contactList);
		System.out.println();
		Files.write(path, contactList);
	}

	public static void main(String[] args) throws IOException {
		String directory = "./src/data";
		String fileName = "contacts.txt";
		Path dataDirectory = Paths.get(directory);
		Path dataFile = Paths.get(directory, fileName);

		addContact(dataFile);
		LoadList(dataFile);
	}
}
