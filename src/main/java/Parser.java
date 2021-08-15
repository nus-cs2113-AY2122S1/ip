import java.util.Arrays;

public class Parser {

    public static Command parse(String input) throws IllegalArgumentException{
        String[] args = input.split(" ");

        Action action;
        String[] params;

        if (args[0].equals("")) {
            action = Action.valueOf("NO_ACTION");
            params = new String[0];
        } else {
            String actionString = translateUserAction(args[0]);
            action = Action.valueOf(actionString);

            // If user's action is invalid, treat it as a new task
            if(action == Action.ADD_TASK) {
                params = new String[1];
                params[0] = input;
            }
            else
                params = Arrays.copyOfRange(args, 1, args.length);
        }

        return new Command(action, params);
    }

    private static String translateUserAction(String actionString){
        switch(actionString) {
            case "done":
                return "DO_TASK";
            case "list":
                return "LIST";
            case "bye":
                return "BYE";
            default:
                return "ADD_TASK";
        }
    }
}
