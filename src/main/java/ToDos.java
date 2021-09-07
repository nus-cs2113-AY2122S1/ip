
public class ToDos extends Task{

    //variables
    public ToDos(String description) {
        super(description);
    }

    //methods
    @Override
    public String toString() {
        return("[T]" + super.toString());
    }
}
