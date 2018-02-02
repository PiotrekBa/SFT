package pl.piotrbartoszak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("")
    public void home(HttpServletResponse response, HttpSession session) throws IOException {
//        String user = (String) session.getAttribute("user");
//        if (user == null) {
//            response.sendRedirect("/login.html");
//        } else if (user.equals("admin")){
//            response.sendRedirect("/admin.html");
//        } else {
//            response.sendRedirect("/user.html");
//        }
        response.sendRedirect("/admin.html");
    }
}
