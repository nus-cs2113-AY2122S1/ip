package seedu.tojava.Duke;
public class Deadline extends Todo{

    protected String by;
    public Deadline(String description) {
        super(description);
        int index = description.indexOf("/") + 1;
        by = description.substring(index, description.length());
//        isDone = false;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String getDescription(){
        int index = description.indexOf("/");
        if(index == -1) return " ";
        return description.substring(0,index);
    }

    public Deadline (String description, String by){
        super(description);
        this.by = by;
        isDone = false;
    }

    @Override
    public String returnType(){
        return "D";
    }
}
