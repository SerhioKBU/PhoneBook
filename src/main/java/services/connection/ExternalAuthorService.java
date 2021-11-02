package services.connection;

import dto.login.LoginUserRequest;
import dto.login.LoginUserResponse;
import dto.registration.RegistrationUserRequest;
import dto.registration.RegistrationUserResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ExternalAuthorService implements AuthorService {
    private static final String URL = "https://mag-contacts-api.herokuapp.com";
    private final ServerService serverService = new ServerService();
    static String token;
    private boolean isAuth;

    @Override
    public boolean login(String login, String password) {

        LoginUserRequest loginRequest = new LoginUserRequest();
        try {

            LoginUserResponse loginResponse =
                    serverService.makePostRequest(URL + "/login",
                            Map.of("Accept", "application/json",
                                    "Content-Type", "application/json"),
                            loginRequest.setLogin(login).setPassword(password),
                            LoginUserResponse.class);
            token = loginResponse.getMessage();

        } catch (RuntimeException exception) {
            isAuth = false;
            throw exception;
        }
        return isAuth = true;
    }

    @Override
    public boolean registration(String login, String password, String birthDay) {
        RegistrationUserRequest registrationRequest = new RegistrationUserRequest();

        try {
            try {
                RegistrationUserResponse registrationResponse =
                        serverService.makePostRequest(URL + "/register",
                                Map.of("Accept", "application/json",
                                        "Content-Type", "application/json"),
                                registrationRequest
                                        .setLogin(login)
                                        .setPassword(password)
                                        .setDateBorn(new SimpleDateFormat("yyyy-MM-dd").parse(birthDay)),
                                RegistrationUserResponse.class);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } catch (RuntimeException exception) {
            throw exception;
        }
        return true;
    }

    @Override
    public boolean isAuthoring() {
        return isAuth;
    }
}
