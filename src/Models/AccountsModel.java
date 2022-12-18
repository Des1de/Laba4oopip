package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsModel {


    private List<Account> _accountList;

    private BooleanProperty isAuthorizationFailed = new SimpleBooleanProperty(false);
    private BooleanProperty isSignedIn = new SimpleBooleanProperty(false);
    private BooleanProperty isRegistrationSuccessful = new SimpleBooleanProperty(false);
    private BooleanProperty isRegistrationFailed = new SimpleBooleanProperty(false);

    public BooleanProperty getIsAuthorizationFailed() { return isAuthorizationFailed; }
    public BooleanProperty getIsSignedIn()
    {
        return isSignedIn;
    }
    public BooleanProperty getIsRegistrationSuccessful(){return isRegistrationSuccessful;}
    public BooleanProperty getIsRegistrationFailed(){return isRegistrationFailed;}
    public AccountsModel()
    {
        _accountList = ReadAccountList();
    }

    public static String authorizedUserName;

    public void RegisterAccount(Account account)
    {
        if(account.getPassword().isEmpty() || account.getLogin().isEmpty())
        {
            isRegistrationFailed.setValue(true);
            return;
        }
        for(Account acc: _accountList)
        {
            if(acc.getLogin().equals(account.getLogin()))
            {
                isRegistrationFailed.setValue(true);
                return;
            }
        }
        _accountList.add(account);
        SaveAccounts(_accountList);
        isRegistrationFailed.setValue(false);
        isRegistrationSuccessful.setValue(true);
    }

    public void Authorization(Account account)
    {
        for(Account acc: _accountList)
        {
            if(acc.getLogin().equals(account.getLogin()))
            {
                if(acc.getPassword().equals(account.getPassword()))
                {
                    isAuthorizationFailed.setValue(false);
                    isSignedIn.setValue(true);
                    authorizedUserName = account.getLogin();
                    return;
                }
                else
                {
                    isAuthorizationFailed.setValue(true);
                }
            }
        }
        isAuthorizationFailed.setValue(true);
    }

    private static void SaveAccounts(List<Account> accountList)
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt", false)))
        {
            oos.writeObject(accountList);
        }
        catch(IOException ex)
        {
            ex.getMessage();
        }
    }
    private static List<Account> ReadAccountList()
    {
        List<Account> accountList = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt")))
        {
            accountList = (List<Account>) ois.readObject();
        }
        catch(Exception ex)
        {
            ex.getMessage();
        }
        return accountList;
    }
}
