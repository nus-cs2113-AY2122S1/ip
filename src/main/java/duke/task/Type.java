package duke.task;

/**
 * Enum to represent the different task types
 *
 * <code>NUMBER_OF_ARGUMENTS</code> int corresponds to number of arguments for respective task (including task itself).
 * <code>PREPOSITION</code> String corresponds to preposition for TimedTasks (by or at).
 */
public enum Type {
    DEADLINE(3, "by"),
    EVENT(3, "at"),
    TODO(2, "");

    public final int NUMBER_OF_ARGUMENTS;
    public final String PREPOSITION;

    Type(int numberOfArguments, String preposition){
        NUMBER_OF_ARGUMENTS = numberOfArguments;
        PREPOSITION = preposition;
    }

    /**
     * Returns a regex that matches any of the task types regardless of case.
     * @return String regex that matches all task types.
     */
    public static String getTaskTypesRegex() {
        String regex = "(?i:";
        for (Type type : Type.values()) {
            regex += type.toString() + ".*|";
        }
        return regex.substring(0, regex.length() - 1) + ')';
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    char getChar() {
        return super.toString().charAt(0);
    }

    static Type getType(String task){
        if (task.length() == 0){
            return null;
        }
        for(Type type : values()){
            if(task.charAt(0) == type.getChar()){
                return type;
            }
        }
        return null;
    }

}
