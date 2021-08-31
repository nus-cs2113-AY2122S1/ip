public class Parser {
    public static Command processCommand(String command) {
        boolean isList = command.equals("list");
        if(isList) return Command.LIST;

        boolean isBye = command.equals("bye");
        if(isBye) return Command.BYE;

        boolean isDone = command.equals("done");
        if(isDone) return Command.DONE;

        boolean isDeadline = command.equals("deadline");
        if(isDeadline) return Command.DEADLINE;

        boolean isEvent = command.equals("event");
        if(isEvent) return Command.EVENT;

        return Command.TODO;
    }
}
