package duke;
/*
Parser to search user input for keywords
*/
public class Parser {
    public Parser() {

    }

    public int delete(String line){
        return line.indexOf("delete");
    }

    public int bye(String line){
        return line.indexOf("bye");
    }

    public int list(String line){
        return line.indexOf("list");
    }

    public int done(String line){
        return line.indexOf("done");
    }

    public int todo(String line){
        return line.indexOf("todo");
    }

    public int deadline(String line){
        return line.indexOf("deadline");
    }

    public int event(String line){
        return line.indexOf("event");
    }

    public int find(String line){
        return line.indexOf("find");
    }

}
