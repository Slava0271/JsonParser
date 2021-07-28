import java.util.LinkedList;
import java.util.List;

public class ParseJson {
    private final static int KEY = 0;
    private final static int VALUE = 1;

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

            char[] charsKey = parsedLines[KEY].toCharArray();
            char[] charsValue = parsedLines[VALUE].toCharArray();

            StringBuilder key = fillFields(charsKey);
            StringBuilder value = fillFields(charsValue);

            parsedObjects.add(new ParsedObject(key.toString(), value.toString()));
        }
        return parsedObjects;
    }

    private StringBuilder fillFields(char[] chars) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '"') {
                i++;
                while (chars[i] != '"') {
                    stringBuilder.append(chars[i]);
                    i++;
                }
                break;
            }
        }
        return stringBuilder;
    }
}
