package seedu.tojava.Duke;

import java.time.LocalDate;

public class Deadline extends Todo{

    protected LocalDate by;
    public Deadline(String description) {
        super(description);
        int index = description.indexOf("/") + 1;
        by = LocalDate.parse(description.substring(index, description.length()).trim());
//        isDone = false;
    }

    public LocalDate getBy(){
        return by;
    }

    @Override
    public String getDescription(){
        int index = description.indexOf("/");
        if(index == -1) return " ";
        return description.substring(0,index);
    }


    @Override
    public String returnType(){
        return "D";
    }
}