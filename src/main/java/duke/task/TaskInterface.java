package duke.task;

public interface TaskInterface {
    void setDone();
    String getDate(String date, boolean isForFile);
    String convertStringToDate();
    String getDescription();
    String getStatusIcon();
    String toString();
    String toFile();
}
