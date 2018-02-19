package pl.piotrbartoszak.service;

public class LoginService {

    public static String[] sessionUser(String in) {
        String[] parts = {"",""};
        try {
            parts = in.split(";");
        } catch (NullPointerException e) {

        }
        return parts;
    }
}
