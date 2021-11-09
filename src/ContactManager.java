import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactManager {

    public static void main(String[] args) throws IOException {
        String directory = "./src/data";
        String fileName = "contacts.txt";
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, fileName);

        System.out.println(dataFile);

        if(Files.notExists(dataDirectory)){
            Files.createDirectories(dataDirectory);
        }
        if(Files.notExists(dataFile)){
            Files.createFile(dataFile);
        }

//       addContact();

    }
}
