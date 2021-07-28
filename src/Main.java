import java.util.LinkedList;

public class Main {
    private static final String fileName = "myJson.json";

    public static void main(String[] args) {

        ParseJson parseJson = new ParseJson(fileName);
        LinkedList<ParsedObject> parsedObjects = parseJson.parseJson();

        for (ParsedObject object : parsedObjects) {
            System.out.println(object.getKey() + " " + object.getValue());
        }
    }
}
