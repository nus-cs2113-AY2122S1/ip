public class Command
{
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
        INVALID;
        private static CommandType[] list = CommandType.values();
        //https://stackoverflow.com/questions/6692664/how-to-get-enum-value-from-index-in-java
        public static CommandType getCommanTypebyIndex(int i) {
            return list[i];
        }

    }

    private final static String[] commandTypeStrs = {"list", "add", "delete", "find", "invalid"};

    public static final String[] getCommandTypeStrs()
    {
        return commandTypeStrs;
    }

    public Command(String s)
    {
        System.out.println("Command received: " + s);
    }
    public Command(CommandType cmdType)
    {
        System.out.print("Command received: ");
        printCommandType(cmdType);
    }

    public void printCommandType(CommandType cmdType)
    {
        System.out.println(commandTypeStrs[cmdType.ordinal()]);
    }






}
