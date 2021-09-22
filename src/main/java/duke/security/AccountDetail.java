package duke.security;

import java.util.Scanner;

public class AccountDetail {
    private String username = "John Doe";
    private String password = "password";

    public AccountDetail() {
        Scanner in = new Scanner(System.in);
        setupUsernamePassword(in);
    }

    public void setUsername(String usernameToInput) {
        if (username.isBlank()) {
            username = "dukeBot";
        } else {
            username = usernameToInput;
        }
    }

    public void setPassword(String passwordToInput) {
        if (passwordToInput.isBlank()) {
            password = "bukeDot";
        } else {
            password = passwordToInput;
        }
    }

    private void setupUsernamePassword(Scanner in) {
        System.out.print("Username [dukeBot]: ");
        setUsername(in.nextLine());
        System.out.print("Password [bukeDot]: ");
        setPassword(in.nextLine());
    }

    public String getUsername() {
        return username;
    }
}
