public class Todo extends Task{
    public static final char IDENTIFIER = 'T';

    Todo(String description){
        super(description);
    }

    @Override
    public String toString(){
        char completeCharacter = isComplete ? 'X' : ' ';
        return "[" + IDENTIFIER + "]" + "[" + completeCharacter + "] " + description;
    }
}
