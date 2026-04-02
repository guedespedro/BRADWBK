package ifsp.ls1.ex1.service.impl;

import ifsp.ls1.ex1.model.User;
import ifsp.ls1.ex1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> usuarios = new ArrayList<>();

    @Override
    public void add(User newUser) {
        usuarios.add(newUser);
    }

    @Override
    public User find(String login) {
        return usuarios.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean remove(String login) {
        return usuarios.removeIf(u -> u.getLogin().equals(login));
    }
}