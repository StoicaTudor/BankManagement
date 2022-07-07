package presentation.ui_action_listeners.menu.general_purpose;

import model.Account;
import presentation.controller.MenuController;
import repository.AccountRepository;
import service.AccountService;
import service.transfer.IInterAccountsMoneyTransfer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferMoneyActionListener implements ActionListener {

    private final MenuController menuController;
    private final AccountService accountService;
    private final IInterAccountsMoneyTransfer interAccountsMoneyTransfer;

    public TransferMoneyActionListener(
            MenuController menuController,
            AccountService accountService,
            IInterAccountsMoneyTransfer interAccountsMoneyTransfer) {

        this.menuController = menuController;
        this.accountService = accountService;
        this.interAccountsMoneyTransfer = interAccountsMoneyTransfer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String firstPartyAccountIdentificationNumber = menuController.getMenuView().getFirstPartyAccount();
        String secondPartyAccountIdentificationNumber = menuController.getMenuView().getSecondPartyAccount();
        float transferSum = Float.parseFloat(menuController.getMenuView().getTransferSum());

        Account firstPartyAccount = null;
        Account secondPartyAccount = null;

        try {
            firstPartyAccount = this.accountService
                    .getAccountByIdentificationNumber(firstPartyAccountIdentificationNumber);
        } catch (Exception ex) {
            this.menuController.getMenuView().alert("First Party Account could not be found. Please insert the correct identification number");
        }

        try {
            secondPartyAccount = this.accountService
                    .getAccountByIdentificationNumber(secondPartyAccountIdentificationNumber);
        } catch (Exception ex) {
            this.menuController.getMenuView().alert("Second Party Account could not be found. Please insert the correct identification number");
        }

        interAccountsMoneyTransfer.transfer(firstPartyAccount, secondPartyAccount, transferSum);
    }
}
