package service;

import model.User;
import repository.UserRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        try {
            this.userRepository.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            this.userRepository.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            this.userRepository.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int id) {
        try {
            Optional<User> user = this.userRepository.getByID(id);
            return user.orElse(new User());
        } catch (SQLException e) {
            e.printStackTrace();
            return new User();
        }
    }

    public List<Optional<User>> getAllUsers() {
        try {
            return this.userRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
