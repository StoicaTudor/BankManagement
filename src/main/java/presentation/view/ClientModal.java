package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientModal extends JFrame {

    private Container container;

    private JLabel idLabel;
    private JTextField idTextField;

    private JLabel nameLabel;
    private JTextField nameTextField;

    private JLabel identityCardNumberLabel;
    private JTextField identityCardNumberTextField;

    private JLabel personalNumericCodeLabel;
    private JTextField personalNumericCodeTextField;

    private JLabel addressLabel;
    private JTextField addressTextField;

    private JButton submitButton;

    private final ModalType modalType;

    public ClientModal(ModalType modalType) {

        this.modalType = modalType;

        initUIElements();
        container.setLayout(null);
        setUIElementsLocationAndSize();
        addUIElementsToContainer();
        initFrame();
    }

    private void initFrame() {
        this.setTitle(this.modalType.toString() + " Client");
        this.setBounds(10, 10, 700, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
    }

    private void initUIElements() {

        container = getContentPane();

        idLabel = new JLabel("Id");
        idTextField = new JTextField();
        idTextField.setEditable(false);

        nameLabel = new JLabel("Name");
        nameTextField = new JTextField();

        identityCardNumberLabel = new JLabel("Identity Card Number");
        identityCardNumberTextField = new JTextField();

        personalNumericCodeLabel = new JLabel("Personal Numeric Code");
        personalNumericCodeTextField = new JTextField();

        addressLabel = new JLabel("Address");
        addressTextField = new JTextField();

        submitButton = new JButton("Submit");
    }

    private void setUIElementsLocationAndSize() {

        idLabel.setBounds(10, 10, 300, 40);
        idTextField.setBounds(210, 10, 300, 40);

        nameLabel.setBounds(10, 110, 300, 40);
        nameTextField.setBounds(210, 110, 300, 40);

        identityCardNumberLabel.setBounds(10, 210, 300, 40);
        identityCardNumberTextField.setBounds(210, 210, 300, 40);

        personalNumericCodeLabel.setBounds(10, 310, 300, 40);
        personalNumericCodeTextField.setBounds(210, 310, 300, 40);

        addressLabel.setBounds(10, 410, 300, 40);
        addressTextField.setBounds(210, 410, 300, 40);

        submitButton.setBounds(10, 510, 150, 30);
    }

    private void addUIElementsToContainer() {
        container.add(idLabel);
        container.add(idTextField);
        container.add(nameLabel);
        container.add(nameTextField);
        container.add(identityCardNumberLabel);
        container.add(identityCardNumberTextField);
        container.add(personalNumericCodeLabel);
        container.add(personalNumericCodeTextField);
        container.add(addressLabel);
        container.add(addressTextField);

        if (!this.modalType.equals(ModalType.VIEW)) {
            container.add(submitButton);
        }
    }

    public ClientModal setActionListenerToSubmitButton(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
        return this;
    }

    public ClientModal setTextFieldValues(
            String id,
            String name,
            String identityCardNumber,
            String personalNumericCode,
            String address) {

        idTextField.setText(id);
        nameTextField.setText(name);
        identityCardNumberTextField.setText(identityCardNumber);
        personalNumericCodeTextField.setText(personalNumericCode);
        addressTextField.setText(address);

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

    public String getName() {
        return this.nameTextField.getText();
    }

    public String getPersonalNumericCode() {
        return this.personalNumericCodeTextField.getText();
    }

    public String getIdentityCardNumber() {
        return this.identityCardNumberTextField.getText();
    }

    public String getAddress() {
        return this.addressTextField.getText();
    }
}
