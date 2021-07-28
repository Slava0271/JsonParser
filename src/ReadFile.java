import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReadFile {
    List<String> readFile(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));

            List<String> lines = new LinkedList<>();
            String line = bufferedReader.readLine();
            while (true) {
                line = bufferedReader.readLine();
                if (line.equals("}")) break;
                lines.add(line);
            }
            return lines;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
