package seedu.tojava.Duke;

import java.time.LocalDate;

public class Event extends Todo{
    protected LocalDate duration;
    public Event(String description) {
        super(description);
        int index = description.indexOf("/") + 1;
        this.duration = LocalDate.parse(description.substring(index, description.length()).trim());
        isDone = false;
    }

    public LocalDate getDuration(){
        return duration;
    }

    @Override
    public String getDescription(){
        int index = description.indexOf("/");
        if(index == -1) return " ";
        return description.substring(0,index);
    }


    @Override
    public String returnType(){
        return "E";
    }
}

