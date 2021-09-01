public class ToDo extends Task{

    protected final static char LETTER = 'T';

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return description;
    }

    public char getLetter() {
        return LETTER;
    }

    public String getDate() {
        return "";
    }

}
