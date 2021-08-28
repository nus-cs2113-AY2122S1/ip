public class InputFilter {

    public String[] separateCommand(String input) {
        return input.split(" ", 2);
    }

    public String[] separateDate(String taskContent) {
        return taskContent.split("/", 2);
    }
}
