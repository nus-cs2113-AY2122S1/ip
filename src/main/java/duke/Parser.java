package duke;

import java.io.IOException;

public class Parser {

    protected boolean isBye;
    protected TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
        isBye = false;
    }

    public void checkCommand(String line) throws DukeException, IOException {
        String[] input = line.split(" ");
        String firstWord = input[0];
        if (input.length == 1) {
            switch (line) {
            case "bye":
                isBye = true;
                break;
            case "list":
                if (tasks.positionCheck == 0) {
                    throw new DukeException(Ui.EMPTY);
                } else {
                    tasks.printList();
                    break;
                }
            case "done":
                throw new DukeException(Ui.UNSPECIFIED_DONE);
            case "delete":
                throw new DukeException(Ui.UNSPECIFIED_DELETE);
            case "find":
                throw new DukeException(Ui.UNSPECIFIED_FIND);
            case "date":
                throw new DukeException(Ui.UNSPECIFIED_DATE);
            default:
                throw new DukeException(Ui.UNSPECIFIED_TASK);
            }
        } else {
            String secondWord = input[1];
            switch (firstWord) {
            case "done":
            case "delete":
                int taskNumber = Integer.parseInt(input[1]);
                int taskIndex = taskNumber - 1;
                if (tasks.positionCheck <= 0) {
                    throw new DukeException(Ui.EMPTY);
                } else if ( (taskNumber > tasks.positionCheck ) || ( taskNumber <= 0) ) {
                    throw new DukeException(Ui.INVALID);
                } else if (firstWord.equals("done")) {
                    tasks.markDone(taskIndex);
                    break;
                } else {
                    tasks.deleteTask(taskIndex);
                    break;
                }
            case "find":
                String keyword = secondWord;
                for (int i = 2 ; i < input.length ; i++ ) {
                    keyword += " " + input[i];
                }
                tasks.printListForFindingTask(keyword);
                break;
            case "date":
                tasks.printListForFindingDate(secondWord);
                break;
            default:
                checkTypeOfTask(line);
                break;
            }
        }
    }

    public void checkTypeOfTask(String line) throws DukeException, IOException {
        String[] input = line.split(" ");
        int length = input.length;
        String firstWord = input[0];
        switch (firstWord) {
        case "deadline":
            tasks.addDeadline(input,length);
            break;
        case "event":
            tasks.addEvent(input,length);
            break;
        case "todo":
            tasks.addTodo(input,length);
            break;
        default:
            throw new DukeException(Ui.UNSPECIFIED_TASK);
        }
    }

}
