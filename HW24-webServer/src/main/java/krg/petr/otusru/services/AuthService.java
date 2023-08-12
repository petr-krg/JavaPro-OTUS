package krg.petr.otusru.services;

public interface AuthService {
    boolean authenticate(String login, String password);
}
