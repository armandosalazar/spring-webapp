package webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersViewController {

    @RequestMapping
    public String index(Model model) {
        String javaVersion = System.getProperty("java.version");
        model.addAttribute("javaVersion", javaVersion);
        return "users";
    }
}
