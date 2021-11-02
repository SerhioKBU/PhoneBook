package services.connection;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InternalAuthorService implements AuthorService{
    private final String pass;
    private final String login;
    private boolean isAuth = false;

    @Override
    public boolean login(String login, String password) {
        return isAuth = this.login.equals(login) && this.pass.equals(password);
    }

    @Override
    public boolean registration(String login, String password, String birthDay) {
        return false;
    }

    @Override
    public boolean isAuthoring() {
        return isAuth;
    }
}
