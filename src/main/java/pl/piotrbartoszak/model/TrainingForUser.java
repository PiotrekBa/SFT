package pl.piotrbartoszak.model;

import pl.piotrbartoszak.entity.Training;
import pl.piotrbartoszak.entity.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrainingForUser {

    private long id;

    private String name;

    private int capacity;

    private boolean sign;

    private LocalDate date;

    private LocalTime time;

    private LocalTime duration;

    private int vacancies;


    public TrainingForUser() {
    }

    public static TrainingForUser forUserfromTraining(Training t, User user) {
        TrainingForUser tfu = new TrainingForUser();
        tfu.setId(t.getId());
        tfu.setName(t.getName());
        tfu.setCapacity(t.getCapacity());
        tfu.setSign(listContain(t.getUsers(), user));
        tfu.setDate(t.getDate());
        tfu.setTime(t.getTime());
        tfu.setDuration(t.getDuration());
        tfu.setVacancies(t.getCapacity()-t.getUsers().size());
        return tfu;
    }

    private static boolean listContain(List<User> users, User user) {
        for(User u : users) {
            if (u.getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }
}
