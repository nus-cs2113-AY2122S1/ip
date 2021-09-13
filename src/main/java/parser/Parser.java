package parser;

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
                action = translateUserAction(actionArg);
            }catch(IllegalArgumentException e){
                throw new IllegalArgumentException(e.getMessage());
            }

            String[] remainingArgs = Arrays.copyOfRange(args, 1, args.length);

            /* Assume that each action only takes 1 main argument => user can specify as many flags they want,
            but the unsupported ones will be ignored */
            String mainArgs = parseFlagArguments(remainingArgs, params);

            if(mainArgs == null)
                throw new IllegalArgumentException("Index not specified");

            switch(action) {
            case DO_TASK:
            case DELETE_TASK:
                params.put("index", mainArgs);
                break;
            case ADD_TASK:
                params.put("type", actionArg);
                params.put("task", mainArgs);
                break;
			default:
				break;
            }
        }

        return new Command(action, params);
    }

    //In the future, this will allow multiple flags to be used
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

    //Translates user action word into an enumerated value, which will define how the params are stored in the parser.Command object
    private static Action translateUserAction(String actionString) throws IllegalArgumentException{
        switch(actionString) {
            case "done":
                return Action.DO_TASK;
            case "list":
                return Action.LIST;
            case "bye":
                return Action.BYE;
            case "todo":
            case "deadline":
            case "event":
                return Action.ADD_TASK;
            case "delete":
                return Action.DELETE_TASK;
            default:
                throw new IllegalArgumentException("Invalid action");
        }
    }
}
