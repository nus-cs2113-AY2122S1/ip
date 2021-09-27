package duke.task;

public enum Type {
    DEADLINE(3, "by"),
    EVENT(3, "at"),
    TODO(2, "");

    public final int NUM_ARGS;
    public final String PREPOSITION;

    Type(int numArgs, String preposition){
        NUM_ARGS = numArgs;
        PREPOSITION = preposition;
    }

    public static String getTypesRegex() {
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
