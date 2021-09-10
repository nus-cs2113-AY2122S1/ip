public class ToDo extends Task {

    public ToDo(String name) throws DukeInvalidInputException {
        super(name);
        this.type = 'T';
    }

}
