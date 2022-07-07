package service.bills;

import model.Account;
import service.AccountService;

public class UtilitiesBillsProcessor implements IBillsProcessor {

    private final AccountService accountService;

    public UtilitiesBillsProcessor(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void processBills(float sum, int accountId) {

        Account account = accountService.getAccount(accountId);

        account.setBalance(account.getBalance() - sum);
        accountService.updateAccount(account);
    }
}
