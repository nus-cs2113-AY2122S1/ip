import java.util.Set;

public class Parser {
    public static void checkCommand(String line) throws EmptyDescriptionException, InvalidCommandException {
        Set<String> validCommands = Set.of("todo", "deadline", "event");
        String[] splitLine = line.split(" ");
        String type = splitLine[0];
        if (!validCommands.contains(type)) {
            throw new InvalidCommandException();
        }
        if (splitLine.length == 1) {
            throw new EmptyDescriptionException();
        }
    }

    public static void handleCommand(String line) {
        String description;
        String date;
        Data.dones.add(false);
        if (line.startsWith("todo")) {
            Data.types.add("T");
            description = line.substring(5);
            Todo todo = new Todo(description);
            Data.descriptions.add(description);
            Data.dates.add("");
            System.out.println("       " + todo.toString(false));
        } else if (line.startsWith("deadline")) {
            Data.types.add("D");
            int separator = line.indexOf("/by");
            description = line.substring(9, separator - 1);
            date = line.substring(separator + 4);
            Deadline deadline = new Deadline(description, date);
            Data.descriptions.add(description);
            Data.dates.add(date);
            System.out.println("       " + deadline.toString(false));
        } else if (line.startsWith("event")) {
            Data.types.add("E");
            int separator = line.indexOf("/at");
            description = line.substring(6, separator - 1);
            date = line.substring(separator + 4);
            Event event = new Event(description, date);
            Data.descriptions.add(description);
            Data.dates.add(date);
            System.out.println("       " + event.toString(false));
        } else {
            Data.types.add("unknown");
        }
    }
}