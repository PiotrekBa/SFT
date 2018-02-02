package pl.piotrbartoszak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piotrbartoszak.entity.Training;
import pl.piotrbartoszak.model.TrainingToView;
import pl.piotrbartoszak.repository.TrainingRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    TrainingRepository trainingRepository;

    @GetMapping("")
    public List<Training> allTrainings() {
        List<Training> trainings = trainingRepository.findAll();
        return trainings;
    }

    @GetMapping("/week")
    public TrainingToView allWeekTrainings(){
        LocalDate startDate = LocalDate.now();
        LocalDate finishDate = startDate.plusDays(7);
        List<Training> training = trainingRepository.findTrainingsAtTheWeek(startDate,finishDate);
        return TrainingToView.fromTrainingList(training,startDate,finishDate);
    }

    @PostMapping("")
    public void addTraining(@RequestBody Training training) {
        System.out.println(training.getName());
    }
}
