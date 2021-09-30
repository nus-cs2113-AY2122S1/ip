package task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Deadline extends Task {
    protected String time;

    private String dateFormatter(String time) throws ParseException {
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("MMM dd yyyy");
        Date date = originalFormat.parse(time);
        return targetFormat.format(date);  // 20120821
    }

    public Deadline(String description, String time) {
        super(description);
        try {
            this.time = dateFormatter(time);
        } catch (ParseException e) {
            this.time = time;
        }
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getTime() + ")";
    }
}
