package pl.piotrbartoszak.controller;


import org.springframework.web.bind.annotation.*;
import pl.piotrbartoszak.service.LoginService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("")
public class HomeController {

    @GetMapping("")
    public void start(HttpServletResponse response,
                      HttpSession session) throws IOException {
        session.setAttribute("user", "");
        response.sendRedirect("/home");
    }

    @GetMapping("/home")
    public void home(HttpServletResponse response,
                     @SessionAttribute("user") String u) throws IOException {

        String[] userSession = LoginService.sessionUser(u);

        if (u.equals("")) {
            response.sendRedirect("login.html");
        } else if (userSession[0].equals("admin")){
            response.sendRedirect("admin.html");
        } else if (userSession[0].equals("user")){
            response.sendRedirect("user-home.html");
        } else {
            response.sendRedirect("login.html");
        }
    }
}
