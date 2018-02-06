package pl.piotrbartoszak.model;

import pl.piotrbartoszak.entity.Training;

import java.time.LocalDate;
import java.util.List;

public class TrainingToViewForUser {

    private List<TrainingForUser> trainings;
    private LocalDate startDate;
    private LocalDate finishDate;

    public List<TrainingForUser> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<TrainingForUser> trainings) {
        this.trainings = trainings;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
