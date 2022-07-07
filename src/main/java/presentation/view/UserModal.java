package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserModal extends JFrame {

    private Container container;

    private JLabel idLabel;
    private JTextField idTextField;

    private JLabel nameLabel;
    private JTextField nameTextField;

    private JButton submitButton;

    private final ModalType modalType;

    public UserModal(ModalType modalType) {

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

        submitButton = new JButton("Submit");
    }

    private void setUIElementsLocationAndSize() {

        idLabel.setBounds(10, 10, 300, 40);
        idTextField.setBounds(210, 10, 300, 40);

        nameLabel.setBounds(10, 110, 300, 40);
        nameTextField.setBounds(210, 110, 300, 40);

        submitButton.setBounds(10, 210, 150, 30);
    }

    private void addUIElementsToContainer() {
        container.add(idLabel);
        container.add(idTextField);
        container.add(nameLabel);
        container.add(nameTextField);

        if (!this.modalType.equals(ModalType.VIEW)) {
            container.add(submitButton);
        }
    }

    public UserModal setActionListenerToSubmitButton(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
        return this;
    }

    public UserModal setTextFieldValues(
            String id,
            String name) {

        idTextField.setText(id);
        nameTextField.setText(name);

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
}
