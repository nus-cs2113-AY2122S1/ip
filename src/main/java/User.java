
public class User {
    private UserServer server;
    private String userName;

    User() {
        this.userName = "default";
        server = new UserServer(userName);
    }

    User(String userName) {
        this.userName = userName;
        server = new UserServer(userName);
    }

    public void startServe() {
        server.serviceBegin();
        this.save();
    }

    public void save() {
        server.save(this.userName);
    }
}
