package webapp.dao;

import webapp.models.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    void registerUser(User user);

    User verify(User user);

    void deleteUser(int id);
}
