package pl.piotrbartoszak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piotrbartoszak.entity.User;
import pl.piotrbartoszak.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    @PostMapping("")
    public void addUser(HttpServletResponse response,
                        @RequestBody User user) throws IOException {
        user.setEnable(true);
        userRepository.save(user);
        response.sendRedirect("/home");
    }

}
