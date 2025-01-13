package webapp.controller.v1;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.dao.UserDAO;
import webapp.models.User;
import webapp.utils.JWTUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UserDAO userDAO;
    private JWTUtil jwtUtil;

    public UsersController(UserDAO userDAO, JWTUtil jwtUtil) {
        this.userDAO = userDAO;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.ok(userDAO.getUsers());
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<String> getUsers(@RequestHeader(value = "Authorization") String token) {
//        if (!verifyToke(toke)) {
//            return null;
//        }
//        return userDAO.getUsers();
        return List.of("user1", "user2");
    }

    public boolean verifyToken(String token) {
//        return jwtUtil.getKey(token) != null;
        return true;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {
        logger.info(user.toString());
//        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
//        String hash = argon2.hash(1, 1024, 1, user.getPassword());
//        user.setPassword(hash);
        userDAO.registerUser(user);
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String toke, @PathVariable int id) {
        if (!verifyToken(toke)) {
            return;
        }
        userDAO.deleteUser(id);
    }

}
