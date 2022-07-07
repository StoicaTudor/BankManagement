package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Menu extends JFrame {

    private Map<Integer, String> usersMap;
    private Map<Integer, String> clientsMap;
    private Map<Integer, String> accountsMap;

    private JComboBox<String> clientsComboBox;
    private JComboBox<String> clientsAccountsComboBox;
    private JComboBox<String> usersComboBox;

    private Container container;

    private JButton addClientInfoButton;
    private JButton updateClientInfoButton;
    private JButton viewClientInfoButton;

    private JButton addClientAccountButton;
    private JButton updateClientAccountButton;
    private JButton viewClientAccountButton;
    private JButton deleteClientAccountButton;

    private JButton addUserButton;
    private JButton updateUserButton;
    private JButton viewUserButton;
    private JButton deleteUserButton;

    private JLabel reportStartDateLabel;
    private JLabel reportEndDateLabel;
    private JTextField reportStartDateTextField;
    private JTextField reportEndDateTextField;
    private JButton generateReportButton;

    private JLabel processUtilitiesSumLabel;
    private JTextField processUtilitiesSumTextField;
    private JButton processUtilitiesButton;

    private JButton transferMoneyButton;
    private JLabel firstPartyAccountLabel;
    private JTextField firstPartyAccountTextField;
    private JLabel secondPartyAccountLabel;
    private JTextField secondPartyTextField;
    private JLabel transferSumLabel;
    private JTextField transferSumTextField;

    private JButton refreshInterfaceButton;

    public Menu() {
        initUIElements();
    }

    public void build() {
        container.setLayout(null);
        setUIElementsLocationAndSize();
        addUIElementsToContainer();
        initFrame();
    }

    private void initFrame() {
        this.setTitle("Menu");
        this.setBounds(10, 10, 1700, 1200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
    }

    private void initUIElements() {

        container = getContentPane();

        clientsComboBox = new JComboBox<>();

        addClientInfoButton = new JButton("Add Client");
        updateClientInfoButton = new JButton("Update Client");
        viewClientInfoButton = new JButton("View Client");

        clientsAccountsComboBox = new JComboBox<>();
        addClientAccountButton = new JButton("Add Account");
        updateClientAccountButton = new JButton("Update Account");
        viewClientAccountButton = new JButton("View Account");
        deleteClientAccountButton = new JButton("Delete Account");

        usersComboBox = new JComboBox<>();
        addUserButton = new JButton("Add User");
        updateUserButton = new JButton("Update User");
        viewUserButton = new JButton("View User");
        deleteUserButton = new JButton("Delete User");

        reportStartDateLabel = new JLabel("Start Date");
        reportEndDateLabel = new JLabel("End Date");
        reportStartDateTextField = new JTextField();
        reportEndDateTextField = new JTextField();
        generateReportButton = new JButton("Generate Report");

        transferMoneyButton = new JButton("Transfer Money");
        firstPartyAccountLabel = new JLabel("First Party Account");
        firstPartyAccountTextField = new JTextField();
        secondPartyAccountLabel = new JLabel("Second Party Account");
        secondPartyTextField = new JTextField();
        transferSumLabel = new JLabel("Transfer Sum");
        transferSumTextField = new JTextField();

        processUtilitiesSumLabel = new JLabel("Sum");
        processUtilitiesSumTextField = new JTextField();
        processUtilitiesButton = new JButton("Process Utilities");

        refreshInterfaceButton = new JButton("Refresh Interface");
    }

    private void setUIElementsLocationAndSize() {
        clientsComboBox.setBounds(10, 10, 500, 40);
        viewClientInfoButton.setBounds(10, 70, 300, 40);
        addClientInfoButton.setBounds(10, 120, 300, 40);
        updateClientInfoButton.setBounds(10, 170, 300, 40);

        clientsAccountsComboBox.setBounds(10, 250, 500, 40);
        addClientAccountButton.setBounds(10, 300, 300, 40);
        updateClientAccountButton.setBounds(10, 350, 300, 40);
        viewClientAccountButton.setBounds(10, 400, 300, 40);
        deleteClientAccountButton.setBounds(10, 450, 300, 40);

        reportStartDateLabel.setBounds(10, 550, 300, 40);
        reportEndDateLabel.setBounds(10, 600, 300, 40);
        reportStartDateTextField.setBounds(100, 550, 300, 40);
        reportEndDateTextField.setBounds(100, 600, 300, 40);
        generateReportButton.setBounds(10, 650, 300, 40);

        usersComboBox.setBounds(10, 750, 500, 40);
        addUserButton.setBounds(10, 800, 300, 40);
        updateUserButton.setBounds(10, 850, 300, 40);
        viewUserButton.setBounds(10, 900, 300, 40);
        deleteUserButton.setBounds(10, 950, 300, 40);

        firstPartyAccountLabel.setBounds(800, 10, 300, 40);
        firstPartyAccountTextField.setBounds(1000, 10, 300, 40);
        secondPartyAccountLabel.setBounds(800, 110, 300, 40);
        secondPartyTextField.setBounds(1000, 110, 300, 40);
        transferSumLabel.setBounds(800, 210, 300, 40);
        transferSumTextField.setBounds(1000, 210, 300, 40);
        transferMoneyButton.setBounds(800, 310, 300, 40);

        processUtilitiesSumLabel.setBounds(800, 400, 300, 40);
        processUtilitiesSumTextField.setBounds(850, 400, 300, 40);
        processUtilitiesButton.setBounds(800, 450, 300, 40);

        refreshInterfaceButton.setBounds(800, 550, 300, 40);
    }

    private void addUIElementsToContainer() {
        // clients
        container.add(clientsComboBox);
        container.add(viewClientInfoButton);
        container.add(addClientInfoButton);
        container.add(updateClientInfoButton);

        // accounts
        container.add(clientsAccountsComboBox);
        container.add(addClientAccountButton);
        container.add(updateClientAccountButton);
        container.add(viewClientAccountButton);
        container.add(deleteClientAccountButton);

        // report
        container.add(reportStartDateLabel);
        container.add(reportEndDateLabel);
        container.add(reportStartDateTextField);
        container.add(reportEndDateTextField);
        container.add(generateReportButton);

        // users
        container.add(usersComboBox);
        container.add(addUserButton);
        container.add(updateUserButton);
        container.add(viewUserButton);
        container.add(deleteUserButton);

        // transfer money
        container.add(transferMoneyButton);
        container.add(firstPartyAccountLabel);
        container.add(firstPartyAccountTextField);
        container.add(secondPartyAccountLabel);
        container.add(secondPartyTextField);
        container.add(transferSumLabel);
        container.add(transferSumTextField);

        // process utilities
        container.add(processUtilitiesButton);
        container.add(processUtilitiesSumLabel);
        container.add(processUtilitiesSumTextField);

        container.add(refreshInterfaceButton);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }

    public Menu setClientsList(HashMap<Integer, String> clientList) {
        clientsComboBox = new JComboBox<>();
        this.clientsMap = clientList;

        for (Map.Entry<Integer, String> currentClient : clientList.entrySet()) {
            String clientName = currentClient.getValue();
            clientsComboBox.addItem(clientName);
        }

        return this;
    }

    public Menu setClientsAccountsList(HashMap<Integer, String> accountsMap) {
        clientsAccountsComboBox = new JComboBox<>();
        this.accountsMap = accountsMap;

        for (Map.Entry<Integer, String> account : accountsMap.entrySet()) {
            String accountName = account.getValue();
            clientsAccountsComboBox.addItem(accountName);
        }

        return this;
    }

    public Menu setUsersList(HashMap<Integer, String> usersMap) {
        usersComboBox = new JComboBox<>();
        this.usersMap = usersMap;

        for (Map.Entry<Integer, String> user : usersMap.entrySet()) {
            String userName = user.getValue();
            usersComboBox.addItem(userName);
        }

        return this;
    }

    public Menu setActionListenerToAddClient(ActionListener actionListener) {
        addClientInfoButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToUpdateClient(ActionListener actionListener) {
        updateClientInfoButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToViewClient(ActionListener actionListener) {
        viewClientInfoButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToViewClientAccount(ActionListener actionListener) {
        viewClientAccountButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToUpdateClientAccount(ActionListener actionListener) {
        updateClientAccountButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToCreateClientAccount(ActionListener actionListener) {
        addClientAccountButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToDeleteClientAccount(ActionListener actionListener) {
        deleteClientAccountButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToAddUser(ActionListener actionListener) {
        addUserButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToUpdateUser(ActionListener actionListener) {
        updateUserButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToViewUser(ActionListener actionListener) {
        viewUserButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToDeleteUser(ActionListener actionListener) {
        deleteUserButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToTransferMoney(ActionListener actionListener) {
        transferMoneyButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToProcessUtilities(ActionListener actionListener) {
        processUtilitiesButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToRefreshInterface(ActionListener actionListener) {
        refreshInterfaceButton.addActionListener(actionListener);
        return this;
    }

    public Menu setActionListenerToGenerateReport(ActionListener actionListener) {
        generateReportButton.addActionListener(actionListener);
        return this;
    }

    public String getStartDate() {
        return reportStartDateTextField.getText();
    }

    public String getEndDate() {
        return reportEndDateTextField.getText();
    }

    public String getProcessUtilitiesSum() {
        return processUtilitiesSumTextField.getText();
    }

    public String getFirstPartyAccount() {
        return firstPartyAccountTextField.getText();
    }

    public String getSecondPartyAccount() {
        return secondPartyTextField.getText();
    }

    public String getTransferSum() {
        return transferSumTextField.getText();
    }

    public int getSelectedClientId() throws Exception {
        for (Map.Entry<Integer, String> client : clientsMap.entrySet()) {
            if (client.getValue().equals(clientsComboBox.getSelectedItem())) {
                return client.getKey();
            }
        }

        throw new Exception("Client not found");
    }

    public Integer getSelectedAccountId() throws Exception {
        for (Map.Entry<Integer, String> account : accountsMap.entrySet()) {
            if (account.getValue().equals(clientsAccountsComboBox.getSelectedItem())) {
                return account.getKey();
            }
        }

        throw new Exception("Account not found");
    }

    public int getSelectedUserId() throws Exception {
        for (Map.Entry<Integer, String> user : usersMap.entrySet()) {
            if (user.getValue().equals(usersComboBox.getSelectedItem())) {
                return user.getKey();
            }
        }

        throw new Exception("User not found");
    }

    public void alert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void main(String[] a) {
        Menu frame = new Menu();
        frame.setVisible(true);
    }
}
