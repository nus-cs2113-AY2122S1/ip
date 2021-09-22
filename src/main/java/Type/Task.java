package Type;

import duke.date.DateValidator;
import duke.date.DateValidatorUsingDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Task implements Serializable{
    protected String description;
    protected boolean isDone = false;
    protected LocalDate timeOfTask = null;
    protected boolean ValidTime = false;

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public LocalDate getTime() {
        return timeOfTask;
    }

    public boolean hasTime() {
        return ValidTime;
    }
    public void printTime() {
        System.out.println(timeOfTask.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    public void setTime(String fullUserInput) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        DateValidator validator = new DateValidatorUsingDateFormat(dateFormatter);
        List<String> chunksOfText = Arrays.asList(fullUserInput.split(" "));
        //create list only with valid date --> constraint: first date
        List<String> suitableDate = chunksOfText.stream()
                .filter(s -> validator.isValid(s))
                .collect(Collectors.toList())
                ;

        if (!suitableDate.isEmpty()) {
            this.timeOfTask = LocalDate.parse(suitableDate.get(0)); //1st date in case of troll
            System.out.println("great! i've recorded a date with the task: " + this.getDescription() + " "
                                + "with the date: " + timeOfTask.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        this.ValidTime = true;
        }
    }
}
