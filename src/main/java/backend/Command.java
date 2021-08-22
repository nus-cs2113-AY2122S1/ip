package backend;

import java.util.Map;
import java.util.HashMap;

public class Command {
    // 1. command type
    // 1.1 list
    // 1.2 add
    // 1.3 delete
    // 1.3 find
    // 2. action
    // 3. time
    // 3.1 todo
    // 3.2 deadline
    // 3.3 event
    public enum CommandType {
        LIST,
        ADD,
        DELETE,
        FIND,
        BYE,
        INVALID;
        private static CommandType[] list = CommandType.values();
        // https://stackoverflow.com/questions/6692664/how-to-get-enum-value-from-index-in-java
        public static CommandType getCommanTypebyIndex(int i) {
            return list[i];
        }
    }

    private static final Map<String, CommandType> CommandStr2Type  = 
        new HashMap<String, CommandType>() {{
            put("list", CommandType.LIST);
            put("add", CommandType.ADD); 
            put("delete", CommandType.DELETE);
            put("find", CommandType.FIND); 
            put("bye", CommandType.BYE); 
            put("invalid", CommandType.INVALID); 
        }};

    private CommandType cmdType;

    /**
     * Constructor
     * @param s
     */
    public Command(String s) {
        // System.out.println("Command received: " + s);
    }

    /**
     * Constructor
     * @param cmdType
     */
    public Command(CommandType cmdType) {
        this.cmdType = cmdType;
        // System.out.print("Command received: ");
        // printCommandType(cmdType);
    }

    public static CommandType getCommandTypebyStr(String cmdStr) {
        return CommandStr2Type.get(cmdStr);
    }

    public CommandType getCommandType() {
        return cmdType;
    }

    public void printCommandType(CommandType cmdType) {
        // System.out.println(commandTypeStrs[cmdType.ordinal()]);
    }

    public Boolean isType(CommandType cmdType)
    {
        return (this.cmdType == cmdType);
    }



}
