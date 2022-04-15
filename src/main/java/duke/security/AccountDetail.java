package duke.security;

import java.util.Scanner;

/**
 * Experimental authentication layer of Duke,
 * for personalized task lists, styling in future implementations
 */
public class AccountDetail {
    private String username = "John Doe";

    public AccountDetail() {
        Scanner in = new Scanner(System.in);
        setupUsernamePassword(in);
    }

    public void setPassword(String passwordToInput) {
        String password = "password";
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

    public void setUsername(String usernameToInput) {
        if (username.isBlank()) {
            username = "dukeBot";
        } else {
            username = usernameToInput;
        }
    }
}
