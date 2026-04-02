package ifsp.ls1.ex1.service;

import ifsp.ls1.ex1.model.User;

public interface UserService {
    void add(User newUser);
    User find(String login);
    boolean remove(String login);
}