package pl.piotrbartoszak.model;

import pl.piotrbartoszak.entity.Training;

import java.time.LocalDate;
import java.time.LocalTime;

public class TrainingToSave {

    private String name;

    private int capacity;

    private boolean visibility;

    private String date;

    private String time;

    private String duration;


    public TrainingToSave() {
    }

    public static Training fromTrainingToSave(TrainingToSave t) {
        Training training = new Training();
        training.setName(t.getName());
        training.setDate(LocalDate.parse(t.getDate()));
        training.setTime(LocalTime.parse(t.getTime()));
        training.setDuration(LocalTime.parse(t.getDuration()));
        training.setCapacity(t.getCapacity());
        training.setVisibility(t.isVisibility());
        return training;
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

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}
