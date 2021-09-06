import java.util.*;

public class Parser {

    public static Command parse(String input) throws IllegalArgumentException{
        HashMap<String, String> params = new HashMap<>();
        String[] args = input.split(" ");
        String actionArg = args[0];
        Action action;

        if (actionArg.equals("")) {
            action = Action.valueOf("NO_ACTION");
        } else {
            String actionString;
            try{
                actionString = translateUserAction(actionArg);
            }catch(IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }

            action = Action.valueOf(actionString);
            String[] remainingArgs = Arrays.copyOfRange(args, 1, args.length);

            String mainArgs = parseFlagArguments(remainingArgs, params);

            switch(action) {
            case DO_TASK:
                if(mainArgs == null)
                    throw new IllegalArgumentException("Index not specified");
                params.put("index", mainArgs);
                break;
            case ADD_TASK:
                if(mainArgs == null)
                    throw new IllegalArgumentException("Task not specified");
                params.put("type", actionArg);
                params.put("task", mainArgs);
                break;
			default:
				break;
            }
        }

        return new Command(action, params);
    }

    private static String parseFlagArguments(String[] args, HashMap<String, String> flagParams) {
        String mainArgument = "";
        int i = 0;
        int numOfArgs = args.length;

        //Will stop scanning upon encountering a flag
        while(i < numOfArgs && !isFlag(args[i])) {
            mainArgument += args[i] + " ";
            i++;
        }
        
        //remove trailing space
        mainArgument = mainArgument.strip();

        // if no flags, just return from here
        if(i == numOfArgs)
            return mainArgument;

        String flagArg="", flag=" ";
        //Start to consume flag
        while(i < numOfArgs) {
            if(isFlag(args[i])) {
            	
            	//ignore flag if already found
            	if(flagParams.get(flag) == null) {
            		
            		//remove leading '/' or any flag characters
            		flag = flag.substring(1);
            		flagArg = flagArg.strip();
            		
    	            flagParams.put(flag, flagArg.strip()); 
    	            
                    flagArg = "";
                    flag = args[i];
            	}
            }
            else {
                flagArg += args[i] + " ";
            }
            
            i++;
        }

        //insert last set of flag argument and remove empty key
        flagParams.remove("");
        
		flag = flag.substring(1);
		flagArg = flagArg.strip();
        flagParams.put(flag, flagArg);
        
        return mainArgument;
    }

    private static boolean isFlag(String s) {
        return s.startsWith("/");
    }

    private static String translateUserAction(String actionString) throws IllegalArgumentException{
        switch(actionString) {
            case "done":
                return "DO_TASK";
            case "list":
                return "LIST";
            case "bye":
                return "BYE";
            case "todo":
            case "deadline":
            case "event":
                return "ADD_TASK";
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }
}
