package webapp.controllers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webapp.dao.UserDAO;
import webapp.models.User;
import webapp.utils.JWTUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String toke) {
        if (!verifyToke(toke)) { return null; }
        return userDAO.getUsers();
    }

    public boolean verifyToke(String toke) {
//        return jwtUtil.getKey(toke) != null;

        return true;
    }

    @RequestMapping(path = "api/users",method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDAO.registerUser(user);
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String toke, @PathVariable int id) {
        if (!verifyToke(toke)) { return; }
        userDAO.deleteUser(id);
    }

}
