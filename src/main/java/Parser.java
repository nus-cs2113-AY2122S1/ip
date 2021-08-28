public class Parser {
    public Parser() {
    }

    public String parseCommand(String command) {
        return command.toLowerCase().trim();
    }

    public Action translateAction(String command){
        String normalizedInput = parseCommand(command);
        if (normalizedInput.equals("list")) {
            return Action.LIST;
        } else if (normalizedInput.equals("bye")) {
            return Action.QUIT;
        } else if (normalizedInput.split(" ")[0].equals("done")) {
            return Action.MARK_DONE;
        } else {
            return Action.ADD;
        }
    }
}
