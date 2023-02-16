package Service;

import Model.Account;

import DAO.AccountDAO;

public class AccountService{
private AccountDAO accountDAO;

public AccountService(){
accountDAO = new AccountDAO();
}

public AccountService(AccountDAO accountDAO){
    this.accountDAO = accountDAO;
}
public Account addAccount(Account account){
    if(account.username != "" && account.password.length()>=4){

    return accountDAO.registerAccount(account);        
    }
    return null;
}

public Account logIn(String username, String password){
    return accountDAO.userLogin(username,password);
}
}















/* 
package Service;
import DAO.AccountDAO;
import Model.Account;
import java.util.List;
















/**
 * 
 * please ignore me  
 * The purpose of a Service class is to contain "business logic" that sits between the web layer (controller) and
 * persistence layer (DAO). That means that the Service class performs tasks that aren't done through the web or
 * SQL: programming tasks like checking that the input is valid, conducting additional security checks, or saving the
 * actions undertaken by the API to a logging file.
 *
 * It's perfectly normal to have Service methods that only contain a single line that calls a DAO method. An
 * application that follows best practices will often have unnecessary code, but this makes the code more
 * readable and maintainable in the long run!
 
public class AccountService extends Account{
     private AccountDAO accountDAO;
   
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    public Account addAccount(Account account) {
       if(account.username != "" && account.password.length() >= 4){
            return accountDAO.CreateNewUsers(account);
    }
        return null;
    }
    public Account PostLogins(String username, String password){
        return accountDAO.ProcessUserLogings(username, password);
    }
     return accountDAO.addAccount(account);
    }
    
    */