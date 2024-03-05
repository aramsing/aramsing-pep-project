package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account logInAccount(Account account) {
        try {
            return accountDAO.logInAccount(account);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Account addAccount(Account account) {
        try {
            return accountDAO.insertAccount(account);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
