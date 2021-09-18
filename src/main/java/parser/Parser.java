package parser;

import parser.command.*;

import java.util.*;

public class Parser {

    public Command parseCommand(String input) {
        HashMap<String, String> params = new HashMap<>();
        Command command;

        String[] args = input.split(" ", 2);
        String actionArg = args[0];

        if(args.length > 1) {
            String arguments = args[1];
            params = parseArguments(arguments);
        }
        command = createNewCommand(actionArg, params);
        return command;
    }

    private Command createNewCommand(String action, HashMap<String, String> params) {
        action = action.toUpperCase();
        Command command;

        switch(action) {
        case "TODO":
        case "EVENT":
        case "DEADLINE":
            params.put("type", action.toLowerCase());
            command = new AddCommand(params);
            break;

        case "LIST":
            command = new ListCommand(params);
            break;

        case "DELETE":
        case "REMOVE":
            command = new DeleteCommand(params);
            break;

        case "DONE":
        case "DO":
            command = new DoCommand(params);
            break;

        case "BYE":
        case "EXIT":
            command = new ExitCommand(params);
            break;

        case "FIND":
        case "MATCH":
            command = new FindCommand(params);
            break;

        default:
            command = new InvalidCommand(params);
            break;
        }

        return command;
    }


    private HashMap<String, String> parseArguments(String args) {

        HashMap<String, String> params = new HashMap<>();
        String[] splitArgs = args.split(" ");

        String mainArgument = "";
        int i = 0;
        int numOfArgs = splitArgs.length;

        //Get the main argument of the command
        while(i < numOfArgs && !isFlag(splitArgs[i])) {
            mainArgument += splitArgs[i] + " ";
            i++;
        }

        params.put("main", mainArgument.strip());
        if(i == numOfArgs)
            return params;

        String flagArg="", flag=" ";
        //Start to consume flag
        for(; i < numOfArgs; i++) {
            if(isFlag(splitArgs[i])) {

                //add flag that was already consumed
            	//add flag only if it is not found yet
            	if(params.get(flag) == null) {
            		
            		putFlagIntoParams(params, flag, flagArg);
    	            
                    flagArg = "";
                    flag = splitArgs[i]; //start tracking newly-found flag
            	}
            }
            else {
                flagArg += splitArgs[i] + " ";
            }
        }

        //insert last set of flag argument and remove empty key
        putFlagIntoParams(params, flag, flagArg);
        params.remove(" ");
        
        return params;
    }

    private void putFlagIntoParams(HashMap<String, String> params, String flag, String flagArg) {
        flag = flag.substring(1);
        flagArg = flagArg.strip();

        params.put(flag, flagArg.strip());
    }

    private boolean isFlag(String s) {
        return s.startsWith("/");
    }
}
