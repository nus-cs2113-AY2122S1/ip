package duke;

import java.util.HashMap;

public class Parser {

    public Parser() {

    }

    public static HashMap<String, String> parseCommand(String command) {
        String[] parts = command.split(" /");
        HashMap<String, String> map = new HashMap<String, String>();
        String[] commandParts = parts[0].split(" ", 2);
        map.put("command", commandParts[0]);
        map.put("param", commandParts.length > 1 ? commandParts[1] : "");
        for (int i = 1; i < parts.length; i++) {
            String[] argParts = parts[i].split(" ", 2);
            if (argParts.length == 2) {
                map.put(argParts[0], argParts[1]);
            }
        }
        return map;
    }
}
