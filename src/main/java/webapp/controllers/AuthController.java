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

    @Autowired
    private UserDAO userDAO;

    @Autowired // Automatically applies dependency injection.
    private JWTUtil jwtUtil;

    @RequestMapping(path = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        if(userDAO.verify(user) != null) {
//            return jwtUtil.create(String.valueOf(userDAO.verify(user).getId()), userDAO.verify(user).getEmail());
        }
        return "FAIL";
    }

}
