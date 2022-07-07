package presentation.controller;

import model.Account;
import model.Client;
import model.User;
import presentation.ui_action_listeners.menu.general_purpose.GenerateReportActionListener;
import presentation.ui_action_listeners.menu.general_purpose.ProcessUtilitiesActionListener;
import presentation.ui_action_listeners.menu.general_purpose.TransferMoneyActionListener;
import presentation.view.*;
import repository.AccountRepository;
import repository.ActionRepository;
import repository.ClientRepository;
import repository.UserRepository;
import service.AccountService;
import service.ActionService;
import service.ClientService;
import service.UserService;
import service.bills.IBillsProcessor;
import service.bills.UtilitiesBillsProcessor;
import service.reports.factory.ReportsGeneratorFactory;
import service.transfer.IInterAccountsMoneyTransfer;
import service.transfer.InterAccountsMoneyTransfer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MenuController {

    private presentation.view.Menu menu;

    private final ClientService clientService;
    private final AccountService accountService;
    private final UserService userService;
    private final IInterAccountsMoneyTransfer interAccountsMoneyTransferService;
    private final IBillsProcessor billsProcessor;
    private final ReportsGeneratorFactory reportsGeneratorFactory;

    public MenuController(
            ClientService clientService,
            AccountService accountService,
            UserService userService,
            IInterAccountsMoneyTransfer interAccountsMoneyTransferService,
            IBillsProcessor billsProcessor,
            ReportsGeneratorFactory reportsGeneratorFactory) {

        this.clientService = clientService;
        this.accountService = accountService;
        this.userService = userService;
        this.interAccountsMoneyTransferService = interAccountsMoneyTransferService;
        this.billsProcessor = billsProcessor;
        this.reportsGeneratorFactory = reportsGeneratorFactory;

        menu = new presentation.view.Menu();
        menu.setClientsList(getClientList())
                .setClientsAccountsList(getClientsAccountsList())
                .setUsersList(getUsersList())
                .build();

        this.addListeners();

        menu.setVisible(true);
    }

    private void addListeners() {
        menu
                .setActionListenerToAddClient(new AddClientActionListener())
                .setActionListenerToUpdateClient(new UpdateClientActionListener())
                .setActionListenerToViewClient(new ViewClientActionListener())
                .setActionListenerToRefreshInterface(new RefreshInterfaceActionListener())
                .setActionListenerToCreateClientAccount(new AddAccountActionListener())
                .setActionListenerToUpdateClientAccount(new UpdateAccountActionListener())
                .setActionListenerToViewClientAccount(new ViewAccountActionListener())
                .setActionListenerToDeleteClientAccount(new DeleteAccountActionListener())
                .setActionListenerToAddUser(new AddUserActionListener())
                .setActionListenerToUpdateUser(new UpdateUserActionListener())
                .setActionListenerToViewUser(new ViewUserActionListener())
                .setActionListenerToDeleteUser(new DeleteUserActionListener())
                .setActionListenerToTransferMoney(new TransferMoneyActionListener(this, accountService, interAccountsMoneyTransferService))
                .setActionListenerToProcessUtilities(new ProcessUtilitiesActionListener(this.getMenuView(), billsProcessor))
                .setActionListenerToGenerateReport(new GenerateReportActionListener(this.getMenuView(), reportsGeneratorFactory, userService));
    }

    private HashMap<Integer, String> getClientList() {

        List<Optional<Client>> clientList = clientService.getAllClients();

        HashMap<Integer, String> clients = new HashMap<>();
        clientList.forEach(client -> client.ifPresent(value -> clients.put(value.getId(), value.getName())));

        return clients;
    }

    private HashMap<Integer, String> getClientsAccountsList() {

        List<Optional<Account>> clientsAccountsList = this.accountService.getAllAccounts();

        HashMap<Integer, String> accounts = new HashMap<>();
        clientsAccountsList.forEach(account -> account.ifPresent(value -> accounts.put(value.getId(), value.getIdentificationNumber())));

        return accounts;
    }

    private HashMap<Integer, String> getUsersList() {

        List<Optional<User>> usersAccountsList = this.userService.getAllUsers();

        HashMap<Integer, String> users = new HashMap<>();
        usersAccountsList.forEach(account -> account.ifPresent(value -> users.put(value.getId(), value.getUsername())));

        return users;
    }

    // --------------------------------------------------

    public String getProcessUtilitiesSum() {
        return menu.getProcessUtilitiesSum();
    }

    // -----------------------------------------------------

    private class UpdateClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            ClientService clientService = new ClientService(new ClientRepository());

            int clientId = 0;

            try {
                clientId = menu.getSelectedClientId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Client client = clientService.getClient(clientId);

            new ClientModalController(
                    new ClientModal(ModalType.UPDATE).setTextFieldValues(
                            String.valueOf(client.getId()),
                            client.getName(),
                            client.getIdentityCard(),
                            client.getPersonalCode(),
                            client.getAddress()
                    ), clientService)
                    .getClientModal()
                    .setVisible(true);
        }
    }

    private class AddClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ClientModalController(
                    new ClientModal(ModalType.CREATE), clientService)
                    .getClientModal()
                    .setVisible(true);
        }
    }

    private class ViewClientActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int clientId = 0;

            try {
                clientId = menu.getSelectedClientId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Client client = clientService.getClient(clientId);

            new ClientModalController(
                    new ClientModal(ModalType.VIEW).setTextFieldValues(
                            String.valueOf(client.getId()),
                            client.getName(),
                            client.getIdentityCard(),
                            client.getPersonalCode(),
                            client.getAddress()
                    ), clientService)
                    .getClientModal()
                    .setVisible(true);
        }
    }

    private class AddAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int clientId = 0;

            try {
                clientId = menu.getSelectedClientId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String clientName = clientService.getClient(clientId).getName();

            new AccountModalController(
                    new AccountModal(ModalType.CREATE)
                            .setTextFieldValues("", clientName, "", "", "", LocalDateTime.now().toString()),
                    accountService, clientService)
                    .getAccountModal()
                    .setVisible(true);
        }
    }

    private class UpdateAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int clientId = 0;

            try {
                clientId = menu.getSelectedClientId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            int accountId = 0;

            try {
                accountId = menu.getSelectedAccountId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String clientName = clientService.getClient(clientId).getName();
            Account account = accountService.getAccount(accountId);

            new AccountModalController(
                    new AccountModal(ModalType.UPDATE).setTextFieldValues(
                            String.valueOf(account.getId()),
                            clientName,
                            account.getIdentificationNumber(),
                            String.valueOf(account.getBalance()),
                            account.getCurrency(),
                            String.valueOf(account.getCreationDate())
                    ), accountService, clientService)
                    .getAccountModal()
                    .setVisible(true);
        }
    }

    private class ViewAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int clientId = 0;

            try {
                clientId = menu.getSelectedClientId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            int accountId = 0;

            try {
                accountId = menu.getSelectedAccountId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            String clientName = clientService.getClient(clientId).getName();
            Account account = accountService.getAccount(accountId);

            new AccountModalController(
                    new AccountModal(ModalType.VIEW).setTextFieldValues(
                            String.valueOf(account.getId()),
                            clientName,
                            account.getIdentificationNumber(),
                            String.valueOf(account.getBalance()),
                            account.getCurrency(),
                            String.valueOf(account.getCreationDate())
                    ), accountService, clientService)
                    .getAccountModal()
                    .setVisible(true);
        }
    }

    private class DeleteAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int accountId = 0;

            try {
                accountId = menu.getSelectedAccountId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            accountService.deleteAccount(accountId);
        }
    }

    private class AddUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            new UserModalController(
                    new UserModal(ModalType.CREATE), userService)
                    .getUserModal()
                    .setVisible(true);
        }
    }

    private class UpdateUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int userId = 0;

            try {
                userId = menu.getSelectedUserId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            User user = userService.getUser(userId);

            new UserModalController(
                    new UserModal(ModalType.UPDATE).setTextFieldValues(
                            String.valueOf(user.getId()),
                            user.getUsername()
                    ), userService)
                    .getUserModal()
                    .setVisible(true);
        }
    }

    private class ViewUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int userId = 0;

            try {
                userId = menu.getSelectedUserId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            User user = userService.getUser(userId);

            new UserModalController(
                    new UserModal(ModalType.UPDATE).setTextFieldValues(
                            String.valueOf(user.getId()),
                            user.getUsername()
                    ), userService)
                    .getUserModal()
                    .setVisible(true);
        }
    }

    private class DeleteUserActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int userId = 0;

            try {
                userId = menu.getSelectedUserId();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            userService.deleteUser(userId);
        }
    }

    private class RefreshInterfaceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshInterface();
        }
    }

    public void refreshInterface() {

        try {
            menu.dispose();

        } catch (NullPointerException ex) {

        }

        menu = new presentation.view.Menu();
        menu.setClientsList(getClientList())
                .setClientsAccountsList(getClientsAccountsList())
                .setUsersList(getUsersList())
                .build();

//        menu.setClientsAccountsList(getClientsAccountsList());
//        menu.setUsersList(getUsersList());
        this.addListeners();
        menu.setVisible(true);
    }

    public Menu getMenuView() {
        return this.menu;
    }

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        ClientRepository clientRepository = new ClientRepository();
        ActionRepository actionRepository = new ActionRepository(userRepository);

        AccountService accountService = new AccountService(new AccountRepository(clientRepository));
        ActionService actionService = new ActionService(actionRepository);

        new MenuController(
                new ClientService(new ClientRepository()),
                accountService,
                new UserService(userRepository),
                new InterAccountsMoneyTransfer(accountService),
                new UtilitiesBillsProcessor(accountService),
                new ReportsGeneratorFactory(actionService));
    }
}
