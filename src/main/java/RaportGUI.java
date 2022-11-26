import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaportGUI {
    private JPanel panel1;
    private JTextField startHourTextField;
    private JTextField endHourTextField;
    private JButton ComputehourButton;
    private JTextField NumberOfProductsTextField;
    private JButton ComputeProductsbutton;
    private JTextField nbOfOrdersTextField;
    private JTextField valueTextField;
    private JButton ComputeValueButton;
    private JTextField DayTextField6;
    private JButton ComputeDayButton;

    public RaportGUI() {


    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JTextField getStartHourTextField() {
        return startHourTextField;
    }

    public JTextField getEndHourTextField() {
        return endHourTextField;
    }

    public JButton getComputehourButton() {
        return ComputehourButton;
    }

    public JTextField getNumberOfProductsTextField() {
        return NumberOfProductsTextField;
    }

    public JButton getComputeProductsbutton() {
        return ComputeProductsbutton;
    }

    public JTextField getNbOfOrdersTextField() {
        return nbOfOrdersTextField;
    }

    public JTextField getValueTextField() {
        return valueTextField;
    }

    public JButton getComputeValueButton() {
        return ComputeValueButton;
    }

    public JTextField getDayTextField6() {
        return DayTextField6;
    }

    public JButton getComputeDayButton() {
        return ComputeDayButton;
    }
}
