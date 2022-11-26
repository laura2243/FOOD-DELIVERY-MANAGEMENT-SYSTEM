import java.io.Serializable;

public class Credentials implements Serializable {
    private String username;
    private String password;
    private int id;

    public Credentials(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }
}
