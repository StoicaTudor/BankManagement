package service;

import model.Account;
import repository.AccountRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        try {
            this.accountRepository.add(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            this.accountRepository.update(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int id) {
        try {
            this.accountRepository.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccount(int id) {
        try {
            Optional<Account> account = this.accountRepository.getByID(id);
            return account.orElse(new Account());
        } catch (SQLException e) {
            e.printStackTrace();
            return new Account();
        }
    }

    public List<Optional<Account>> getAllAccounts() {
        try {
            return this.accountRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Account getAccountByIdentificationNumber(String identificationNumber) throws Exception {
        return this.accountRepository
                .getByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new Exception("First Party Account could not be found. Please insert the correct identification number"));
    }
}
