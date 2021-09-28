package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected String type = "D";
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor to create Deadline object.
     *
     * @param description Deadline description.
     * @param by The time the deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (by.trim().contains("/")) {
            String formattedBy = getCorrectFormat(by);
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
                /* fill in missing '0's and ':'s int correct orientation */
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
     * Retrieve MoreDetails (timings) of deadlines.
     *
     * @return timings of the Deadline.
     */
    public String getMoreDetails() {
        return this.by;
    }

    /**
     * Retrieve the task type.
     *
     * @return Task type: "D".
     */
    public String getType() {
        return type;
    }

    /**
     * Overrides string representation to display.
     *
     * @return string representation to display.
     */
    public String toString() {
        return (by.contains("/")) ? ("[D]" + super.toString() + " (by: " + displayDateTime()) : ("[D]" + super.toString() + " (by: " + by + ")");
    }
}