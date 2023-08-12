package krg.petr.otusru.services;

import krg.petr.otusru.crm.service.DBServiceClient;

public class AuthServiceImpl implements AuthService {

    private final DBServiceClient dbServiceClient;

    public AuthServiceImpl(DBServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return dbServiceClient.findByLogin(login)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
