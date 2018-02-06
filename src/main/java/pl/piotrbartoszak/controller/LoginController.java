package pl.piotrbartoszak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piotrbartoszak.entity.Admin;
import pl.piotrbartoszak.entity.User;
import pl.piotrbartoszak.model.Login;
import pl.piotrbartoszak.repository.AdminRepository;
import pl.piotrbartoszak.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/check")
    public void checkLogin(@RequestBody Login login,
                           HttpSession session,
                           HttpServletResponse response
                          ) throws IOException {
        Admin admin = adminRepository.findByEmail(login.getEmail());
        System.out.println(admin);
        if (admin != null) {
            if (admin.getPassword().equals(login.getPassword())){
                session.setAttribute("user", admin);
            }
        } else {
            User user = userRepository.findByEmail(login.getEmail());
            if (user != null) {
                if(user.getPassword().equals(login.getPassword())) {
                    session.setAttribute("user", user);
                }
            }
        }
        response.sendRedirect("/home");
    }
}
