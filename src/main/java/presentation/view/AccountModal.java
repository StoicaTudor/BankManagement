package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccountModal extends JFrame {

    private Container container;

    private JLabel idLabel;
    private JTextField idTextField;

    private JLabel clientLabel;
    private JTextField clientTextField;

    private JLabel identificationNumberLabel;
    private JTextField identificationNumberTextField;

    private JLabel sumLabel;
    private JTextField sumTextField;

    private JLabel currencyLabel;
    private JTextField currencyTextField;

    private JLabel creationDateLabel;
    private JTextField creationDateTextField;

    private JButton submitButton;

    private final ModalType modalType;

    public AccountModal(ModalType modalType) {

        this.modalType = modalType;

        initUIElements();
        container.setLayout(null);
        setUIElementsLocationAndSize();
        addUIElementsToContainer();
        initFrame();
    }

    private void initFrame() {
        this.setTitle(this.modalType.toString() + " Account");
        this.setBounds(10, 10, 700, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
    }

    private void initUIElements() {

        container = getContentPane();

        idLabel = new JLabel("Id");
        idTextField = new JTextField();
        idTextField.setEditable(false);

        clientLabel = new JLabel("Client Name");
        clientTextField = new JTextField();
        clientTextField.setEditable(false);

        identificationNumberLabel = new JLabel("Identification Number");
        identificationNumberTextField = new JTextField();

        sumLabel = new JLabel("Balance");
        sumTextField = new JTextField();

        currencyLabel = new JLabel("Currency");
        currencyTextField = new JTextField();

        creationDateLabel = new JLabel("Creation date");
        creationDateTextField = new JTextField();
        creationDateTextField.setEditable(false);

        submitButton = new JButton("Submit");
    }

    private void setUIElementsLocationAndSize() {

        idLabel.setBounds(10, 10, 300, 40);
        idTextField.setBounds(210, 10, 300, 40);

        clientLabel.setBounds(10, 110, 300, 40);
        clientTextField.setBounds(210, 110, 300, 40);

        identificationNumberLabel.setBounds(10, 210, 300, 40);
        identificationNumberTextField.setBounds(210, 210, 300, 40);

        sumLabel.setBounds(10, 310, 300, 40);
        sumTextField.setBounds(210, 310, 300, 40);

        currencyLabel.setBounds(10, 410, 300, 40);
        currencyTextField.setBounds(210, 410, 300, 40);

        creationDateLabel.setBounds(10, 510, 300, 40);
        creationDateTextField.setBounds(210, 510, 300, 40);

        submitButton.setBounds(10, 610, 150, 30);
    }

    private void addUIElementsToContainer() {
        container.add(idLabel);
        container.add(idTextField);
        container.add(clientLabel);
        container.add(clientTextField);
        container.add(identificationNumberLabel);
        container.add(identificationNumberTextField);
        container.add(sumLabel);
        container.add(sumTextField);
        container.add(currencyLabel);
        container.add(currencyTextField);
        container.add(creationDateLabel);
        container.add(creationDateTextField);

        if (!this.modalType.equals(ModalType.VIEW)) {
            container.add(submitButton);
        }
    }

    public AccountModal setActionListenerToSubmitButton(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
        return this;
    }

    public AccountModal setTextFieldValues(
            String id,
            String clientName,
            String identificationNumber,
            String sum,
            String currency,
            String creationDate) {

        idTextField.setText(String.valueOf(id));
        clientTextField.setText(clientName);
        identificationNumberTextField.setText(identificationNumber);
        sumTextField.setText(sum);
        currencyTextField.setText(currency);
        creationDateTextField.setText(creationDate);

        return this;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }

    public ModalType getModalType() {
        return this.modalType;
    }

    public String getId() {
        return this.idTextField.getText();
    }

    public String getClientName() {
        return this.clientTextField.getText();
    }

    public String getIdentificationNumber() {
        return this.identificationNumberTextField.getText();
    }

    public String getSum() {
        return this.sumTextField.getText();
    }

    public String getCurrency() {
        return this.currencyTextField.getText();
    }

    public String getCreationDate() {
        return this.creationDateTextField.getText();
    }
}
