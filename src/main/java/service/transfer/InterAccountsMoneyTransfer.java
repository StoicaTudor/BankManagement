package service.transfer;

import model.Account;
import repository.AccountRepository;
import service.AccountService;

import java.sql.SQLException;

public class InterAccountsMoneyTransfer implements IInterAccountsMoneyTransfer {

    private final AccountService accountService;

    public InterAccountsMoneyTransfer(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void transfer(Account firstPartyAccount, Account secondPartyAccount, float amount) {

        firstPartyAccount.setBalance(firstPartyAccount.getBalance() - amount);
        secondPartyAccount.setBalance(secondPartyAccount.getBalance() + amount);

        accountService.updateAccount(firstPartyAccount);
        accountService.updateAccount(secondPartyAccount);
    }
}
