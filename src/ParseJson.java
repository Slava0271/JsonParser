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

            char[] charsKey = parsedLines[0].toCharArray();
            char[] charsValue = parsedLines[1].toCharArray();

            StringBuilder key = fill(charsKey);
            StringBuilder value = fill(charsValue);

            parsedObjects.add(new ParsedObject(key.toString(), value.toString()));
        }
        return parsedObjects;
    }

    private StringBuilder fill(char[] chars){
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
