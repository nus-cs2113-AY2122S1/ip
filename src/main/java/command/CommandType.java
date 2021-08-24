package command;

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
    public static CommandType getCommandTypebyIndex(int i) {
        return list[i];
    }
}
