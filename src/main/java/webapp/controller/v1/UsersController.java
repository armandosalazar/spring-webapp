package webapp.controller.v1;

import at.favre.lib.crypto.bcrypt.BCrypt;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.dao.UserDAO;
import webapp.models.User;
import webapp.repository.UserRepository;
import webapp.service.UserService;
import webapp.utils.JWTUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UserDAO userDAO;
    private final UserService userService;
    private JWTUtil jwtUtil;

    public UsersController(UserDAO userDAO, UserService userService, JWTUtil jwtUtil) {
        this.userDAO = userDAO;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.ok(userDAO.getUsers());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        logger.info(user.toString());

        String hashed = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(6, user.getPassword().toCharArray());
        user.setPassword(hashed);
        User userSaved =  userService.save(user);

        return ResponseEntity.ok(userSaved);
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String toke, @PathVariable int id) {
        if (!verifyToken(toke)) {
            return;
        }
        userDAO.deleteUser(id);
    }

}
