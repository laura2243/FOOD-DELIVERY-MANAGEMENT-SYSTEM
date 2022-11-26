import java.io.Serializable;
import java.util.ArrayList;

public class LogInCredentials implements Serializable {


    private ArrayList<Credentials> logInCredentialsAdmin = new ArrayList<>();
    private ArrayList<Credentials> logInCredentialsEmployee = new ArrayList<>();
    private ArrayList<Credentials> logInCredentialsClient = new ArrayList<>();

    public ArrayList<Credentials> getLogInCredentialsAdmin() {
        return logInCredentialsAdmin;
    }

    public ArrayList<Credentials> getLogInCredentialsEmployee() {
        return logInCredentialsEmployee;
    }

    public ArrayList<Credentials> getLogInCredentialsClient() {
        return logInCredentialsClient;
    }

    public ArrayList<Credentials> addGetLogInCredentialsAdmin(Credentials logInCredentials) {
        logInCredentialsAdmin.add(logInCredentials);
        return logInCredentialsAdmin;
    }

    public ArrayList<Credentials> addGetLogInCredentialsEmployee(Credentials logInCredentials) {
        logInCredentialsEmployee.add(logInCredentials);
        return logInCredentialsEmployee;
    }

    public ArrayList<Credentials> addGetLogInCredentialsClient(Credentials logInCredentials) {
        logInCredentialsClient.add(logInCredentials);
        return logInCredentialsClient;
    }
}
