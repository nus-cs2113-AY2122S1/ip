
public class User {
    private UserServer server = new UserServer();
    private String userName;

    User() {
        this.userName = "default";
    }

    User(String userName) {
        this.userName = userName;
    }

    public void startServe() {
        server.serviceBegin();
    }

    public void save() {
        //To be implemented
    }
}
