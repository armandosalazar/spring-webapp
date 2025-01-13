package webapp.controller.v1;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webapp.dao.UserDAO;
import webapp.models.User;
import webapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private final Logger logger = LoggerFactory.getLogger(UsersController.class);
    private final UserDAO userDAO;
    private final UserService userService;

    @Autowired
    public UsersController(UserDAO userDAO, UserService userService) {
        this.userDAO = userDAO;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userDAO.getUsers());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        logger.info(user.toString());

        String hashed = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(6, user.getPassword().toCharArray());
        user.setPassword(hashed);
        User userSaved = userService.save(user);

        return ResponseEntity.ok(userSaved);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User userToUpdate = userService.findUserById(id);
        logger.info(userToUpdate.toString());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setPassword(BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(6, user.getPassword().toCharArray()));
        return userService.save(userToUpdate);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public User deleteUser(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable int id) {
        logger.info("Token: {}", token);
        return userService.delete(id);
    }

}
