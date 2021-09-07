package kitty;

import kitty.task.Task;
import kitty.userinterface.Ui;
import kitty.userinterface.UiHandler;

public class Kitty {
    public static final int MAX_TASKS = 100;
    public static Task[] tasks = new Task[MAX_TASKS];

    public static void main(String[] args) {
        Ui.greet();
        UiHandler.startApp();
    }
}
