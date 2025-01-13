package webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webapp.dao.UserDAO;
import webapp.models.User;
import webapp.utils.JWTUtil;

@RestController
public class AuthController {

    private final UserDAO userDAO;

    // Automatically applies dependency injection.
    private final JWTUtil jwtUtil;

    public AuthController(UserDAO userDAO, JWTUtil jwtUtil) {
        this.userDAO = userDAO;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(path = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        if (userDAO.verify(user) != null) {
//            return jwtUtil.create(String.valueOf(userDAO.verify(user).getId()), userDAO.verify(user).getEmail());
        }
        return "FAIL";
    }

}
