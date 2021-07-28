import java.util.LinkedList;
import java.util.List;

public class ParseJson {
    private final String fileName;

    private List<String> list;

    public ParseJson(String fileName) {
        this.fileName = fileName;
        initList();
    }

    public String getFileName() {
        return fileName;
    }

    private void initList() {
        list = new ReadFile().readFile(fileName);
    }

    public LinkedList<ParsedObject> parseJson() {
        LinkedList<ParsedObject> parsedObjects = new LinkedList<>();
        for (String line :
                list) {
            String[] parsedLines = line.split(":");
            StringBuilder key = new StringBuilder();
            StringBuilder value = new StringBuilder();

            char[] charsKey = parsedLines[0].toCharArray();
            char[] charsValue = parsedLines[1].toCharArray();

            for (int i = 0; i < charsKey.length; i++) {
                if (charsKey[i] == '"') {
                    i++;
                    while (charsKey[i] != '"') {
                        key.append(charsKey[i]);
                        i++;
                    }
                    break;
                }
            }

            for (int i = 0; i < charsValue.length; i++) {
                if (charsValue[i] == '"') {
                    i++;
                    while (charsValue[i] != '"') {
                        value.append(charsValue[i]);
                        i++;
                    }
                    break;
                }
            }

            parsedObjects.add(new ParsedObject(key.toString(), value.toString()));
        }
        return parsedObjects;
    }
}
