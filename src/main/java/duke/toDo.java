package duke;

/*
toDo checks input validity formats it based on user input
*/
public class toDo extends task{
    protected String description;

    public toDo(String description) throws DukeException{
        this.description=description;
        if(this.description.isEmpty()==true||this.description.trim().isEmpty()==true){
            throw new DukeException();
        }
    }

    @Override
    public String toString() {
        return this.description;
    }
}