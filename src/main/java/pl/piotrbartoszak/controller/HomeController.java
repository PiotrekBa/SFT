package pl.piotrbartoszak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("")
public class HomeController {

    @GetMapping("")
    public void start(HttpServletResponse response) throws IOException {
        response.sendRedirect("/home");
    }

    @GetMapping("/home")
    public void home(HttpServletResponse response,
                     HttpSession session) throws IOException {
        String user = (String) session.getAttribute("user");
        String name = null;
        try {
            name = user.split(";")[0];
        } catch (NullPointerException e) {
        }
        if (user == null) {
            response.sendRedirect("login.html");
        } else if (name.equals("admin")){
            response.sendRedirect("admin.html");
        } else if (name.equals("user")){
            response.sendRedirect("user.html");
        } else {
            response.sendRedirect("login.html");
        }
    }
}
