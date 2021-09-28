package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected String type = "E";
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor to create Event object.
     *
     * @param description Event description.
     * @param at Event timings.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        if (at.trim().contains("/")) {
            String formattedBy = getCorrectFormat(at);
            String[] tempStr = formattedBy.split(" ");
            this.date = LocalDate.parse(tempStr[0]);
            if (tempStr.length==2)
                this.time = LocalTime.parse(tempStr[1]);
        }
    }

    /**
     * Retrieve correct format to parse into LocalDate and LocalTime.
     *
     * @param by stored by String.
     * @return correct format to parse into LocalDate and Local Time.
     */
    private String getCorrectFormat(String by){

        StringBuilder toParse = new StringBuilder();
        String[] dateTime = by.split(" ");
        String[] tempDate = dateTime[0].trim().split("/");
        String toStore = "";
        if (tempDate.length == 3){
            for (int i = 2; i > 0; i--) {
                toParse.append(tempDate[i]).append("-");
            }
            toParse.append(tempDate[0]);
            toStore += toParse + " ";
        }

        if (dateTime.length > 1) {
            toParse = new StringBuilder();
            int missingDigits = 6 - dateTime[1].trim().length();
            for (int i = 0; i < dateTime[1].trim().length(); i++) {
                char c = dateTime[1].charAt(i);
                toParse.append((i != 0 && i % 2 == 0) ? ":" + c : c);
            }
            for (int i=0 ; i<missingDigits;i++){
                /** fill in missing '0's and ':'s int correct orientation */
                toParse.append((missingDigits % 2 == i % 2) ? ":0" : "0");
            }
            toStore += toParse;
        }
        return toStore;
    }

    private String displayDateTime() {
        String outputString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        if (time!=null) {
            outputString += time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        }
        return outputString + ")";
    }

    /**
     * Retrieve More details (timings) of events.
     *
     * @return timings of the Event.
     */
    public String getMoreDetails() {
        return this.at;
    }

    /**
     * Retrieve the task type.
     *
     * @return Task type: "E".
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return (at.contains("/")) ? ("[E]" + super.toString() + " (at: " + displayDateTime()) : ("[E]" + super.toString() + " (at: " + at + ")");
    }
}