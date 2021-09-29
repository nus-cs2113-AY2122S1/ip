package duke;

public class Parser {
    public static void parser(String line) { //determines which command to choose
        if (line.equals("list")) {
            TaskList.list();
        } else if ((line.split(" ")[0].equals("done"))) {
            try {
                int doneNumber = Integer.parseInt((line.split(" ")[1]));
                TaskList.setDone(doneNumber);
            } catch (Exception DukeException) {
                Ui.displayTaskNoMissing();
            }

        } else if (line.split(" ")[0].equals("deadline") /*&& line.contains("/by")*/) {
            try {
                TaskList.setDeadline(line.split("deadline ")[1], line.split("/by ")[1]);
            } catch (Exception DukeException) {
                Ui.displayDeadlineError();
            }

        } else if (line.split(" ")[0].equals("event") /*&& line.contains("/at")*/) {
            try{
                TaskList.setEvent(line.split("event ")[1], line.split("/at ")[1]);
            } catch(Exception DukeException){
                Ui.displayEventError();
            }

        } else if ((line.split(" ")[0].equals("todo"))) {
            try {
                TaskList.setTodo(line.split("todo ")[1]);
            } catch (Exception DukeException) {
                Ui.displayTodoError();
            }
        } else if ((line.split(" ")[0].equals("delete"))) {
            try {
                int deleteNumber = Integer.parseInt((line.split(" ")[1]));
                TaskList.deleteTask(deleteNumber);
            } catch (Exception DukeException) {
                Ui.displayDeleteNoError();
            }
        }
        else if ((line.split(" ")[0].equals("find"))) {
            try {
                String tobeSearched = ((line.split(" ")[1]));
                TaskList.search(tobeSearched);
            } catch (Exception DukeException) {
                Ui.displayTaskNoMissing();
            }
        }

        else{
            if(!(line.split(" ")[0].equals("bye"))){
                Ui.displayGeneralFormattingError();
            }
        }

        Ui.printHorizontalLine();
    }
}
