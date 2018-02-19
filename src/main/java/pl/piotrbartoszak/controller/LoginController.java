package pl.piotrbartoszak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.piotrbartoszak.entity.Admin;
import pl.piotrbartoszak.entity.User;
import pl.piotrbartoszak.model.Login;
import pl.piotrbartoszak.repository.AdminRepository;
import pl.piotrbartoszak.repository.UserRepository;
import pl.piotrbartoszak.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/check")
    public void checkLogin(@RequestBody Login login,
                           HttpServletResponse response,
                           Model model) throws IOException {
        Admin admin = adminRepository.findByEmail(login.getEmail());
        if (admin != null) {
            if (admin.getPassword().equals(login.getPassword())){
                model.addAttribute("user", "admin;"+admin.getId());
            }
        } else {
            User user = userRepository.findByEmail(login.getEmail());
            if (user != null) {
                if(user.getPassword().equals(login.getPassword())) {
                    model.addAttribute("user", "user;"+ user.getId());
                }
            }
        }
        response.sendRedirect("/home");
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response,
                       Model model) throws IOException {
        model.addAttribute("user", "");
        response.sendRedirect("/home");
    }

    @GetMapping("/check-admin")
    public String checkAdmin(@SessionAttribute("user") String u,
                           HttpServletResponse response) throws IOException {
        String[] userSession = LoginService.sessionUser(u);
        if (!userSession[0].equals("admin")) {
            return "";
        }
        return "good";
    }
}
