package presentation.controller;

import model.Account;
import model.Client;
import presentation.view.AccountModal;
import service.AccountService;
import service.ClientService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class AccountModalController {

    private final AccountModal accountModal;
    private final AccountService accountService;
    private final ClientService clientService;

    public AccountModalController(
            AccountModal accountModal,
            AccountService accountService,
            ClientService clientService) {

        this.accountModal = accountModal;
        this.accountService = accountService;
        this.clientService = clientService;

        this.addActionListeners();

        this.accountModal.setVisible(true);
    }

    private void addActionListeners() {
        this.accountModal.setActionListenerToSubmitButton(new SubmitButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int id = 0;

            try {
                id = Integer.parseInt(accountModal.getId());
            } catch (NumberFormatException ex) {

            }

            String clientName = accountModal.getClientName();
            String identificationNumber = accountModal.getIdentificationNumber();
            float balance = Float.parseFloat(accountModal.getSum());
            String currency = accountModal.getCurrency();
            LocalDateTime creationDate = LocalDateTime.now();

            Account account = new Account()
                    .setId(id)
                    .setClient(clientService.getClient(clientName))
                    .setIdentificationNumber(identificationNumber)
                    .setBalance(balance)
                    .setCurrency(currency)
                    .setCreationDate(creationDate);

            switch (accountModal.getModalType()) {
                case CREATE -> accountService.createAccount(account);
                case UPDATE -> accountService.updateAccount(account);
            }
        }
    }

    public AccountModal getAccountModal() {
        return this.accountModal;
    }
}
