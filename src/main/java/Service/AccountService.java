package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    /*
     * Constructor
     */
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    /*
     * Constructor Overridden
     * Initializes specific instance of Account Service
     */
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /*
     * Business logic to login to our account
     * Go between from the back to front end
     */
    public Account logInAccount(Account account) {
        return accountDAO.logInAccount(account); // returns the logged in account from the database
    }

    /*
     * Business logic to create a new account
     * Go between from the back to front end
     */
    public Account addAccount(Account account) {
        if ((account.getUsername() == "") || (account.getPassword().length() < 4)) { // if the account username is blank and the password is less than four characters, return null
            return null;
        }
        return accountDAO.insertAccount(account); // returns the newly created account from the database
    }
}
