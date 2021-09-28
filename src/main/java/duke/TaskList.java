package duke;

import duke.DukeExceptions.InvalidValueException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask (Task newTask) {
        this.list.add(newTask);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public Task markAsDone(String command) {
        int index = getIndex(command) - 1;
        Task completedTask = list.get(index);
        completedTask.markAsDone();
        list.get(index).markAsDone();
        return completedTask;
    }

    public Task deleteTask(int index) {
        Task targetTask = list.get(index - 1);
        list.remove(index - 1);
        return targetTask;
    }

    public static String getTodo (String command) {
        return command.substring(command.indexOf(" ") + 1);
    }

    public String getDescription (String command) throws InvalidValueException {
        if (!command.contains("/"))
            throw new InvalidValueException("Missing detail demarcator: [/by ] or [/at ]");
        String desc = command.substring(command.indexOf(" ") + 1, command.indexOf("/"));
        if (desc.trim().equals(""))
            throw new InvalidValueException("Missing Description in command");
        return desc;
    }

    public String getMoreDetails(String command) throws InvalidValueException{
        String moreDetails = command.substring(command.indexOf("/") + 4);
        if (moreDetails.trim().equals(""))
            throw new InvalidValueException("Missing Required Extra Details");
        if (moreDetails.trim().contains("/")) {
            moreDetails = getCorrectFormat(moreDetails);
        }
        return moreDetails;
    }

    private String getCorrectFormat(String moreDetails) {

        StringBuilder toParse = new StringBuilder();
        String[] dateTime = moreDetails.split(" ");
        String[] tempDate = dateTime[0].trim().split("/");

        for (int i=2; i > 0; i--) {
            toParse.append(tempDate[i]).append("-");
        }
        LocalDate date = LocalDate.parse(toParse + tempDate[0]);
        moreDetails = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";

        if (dateTime.length > 1) {
            toParse = new StringBuilder();
            int missingDigits = 6 - dateTime[1].trim().length();
            for (int i = 0; i < dateTime[1].trim().length(); i++) {
                char c = dateTime[1].charAt(i);
                toParse.append((i != 0 && i % 2 == 0) ? ":" + c : c);
            }
            for (int i=0 ; i<missingDigits;i++){
                toParse.append((missingDigits % 2 == i % 2) ? ":0" : "0");
            }
            LocalTime time = LocalTime.parse(toParse.toString());
            moreDetails += time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        }

        return moreDetails;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    private static int getIndex(String command) {
        String index = command.substring(command.indexOf(" ") + 1);
        return Integer.parseInt(index.trim());
    }
}
