package by.kastapravau.jwtapp.dto;

public class UserRequest {

    private String login;
    private String password;
    private String fullName;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isValid() {
        return login != null
                && password != null
                && fullName != null
                && !login.isBlank()
                && !password.isBlank()
                && !fullName.isBlank();
    }
}
