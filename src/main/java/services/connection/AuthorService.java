package services.connection;

public interface AuthorService {
    boolean login(String login, String password);
    boolean registration(String login, String password, String birthDay);
    boolean isAuthoring();
}
