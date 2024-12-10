import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class testFile {
    public static void main(String[] args) throws IOException {
        BufferedReader manager_info = new BufferedReader(new FileReader("C:\\Users\\Mervin Ooi\\IdeaProjects\\OOP Assignment\\src\\Manager_Info.txt"));

        String line;

        while(((line = manager_info.readLine()) != null)) {
            System.out.println(line);
        }

        manager_info.close();
    }

}





