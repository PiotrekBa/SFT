package pl.piotrbartoszak.model;

import pl.piotrbartoszak.entity.Training;

import java.time.LocalDate;
import java.util.List;

public class TrainingToView {

   private List<Training> trainings;
   private LocalDate startDate;
   private LocalDate finishDate;

   public static TrainingToView fromTrainingList(List<Training> trainings,
                                          LocalDate startDate,
                                          LocalDate finishDate) {
       TrainingToView ttv = new TrainingToView();
       ttv.trainings = trainings;
       ttv.startDate = startDate;
       ttv.finishDate = finishDate;
       return ttv;
   }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
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
