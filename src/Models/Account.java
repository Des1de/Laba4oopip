package Models;

import java.io.Serializable;

public class Account implements Serializable {
    private String _login;
    private String _password;
    public String getLogin() {
        return _login;
    }

    public String getPassword() {
        return _password;
    }
    public Account(String login, String password)
    {
        _login = login;
        _password = password;
    }
}
