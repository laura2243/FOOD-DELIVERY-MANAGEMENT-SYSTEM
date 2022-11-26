import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel rootMain;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton createNewAccountButton;
    private JButton LoginButton;


    public Login() {



    }

    public JPanel getMainRootPanel() {
        return rootMain;
    }

    public JPanel getRootMain() {
        return rootMain;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JButton getCreateNewAccountButton() {
        return createNewAccountButton;
    }

    public JButton getLoginButton() {
        return LoginButton;
    }
}
