package kitty;

import kitty.task.Task;
import kitty.userinterface.Ui;
import kitty.userinterface.UiHandler;
import java.util.ArrayList;

public class Kitty {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Ui.greet();
        UiHandler.startApp();
    }
}
