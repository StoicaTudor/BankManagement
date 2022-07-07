package service.transfer;

import model.Account;

import java.sql.SQLException;

public interface IInterAccountsMoneyTransfer {
    void transfer(Account firstPartyAccount, Account secondPartyAccount, float amount);
}
