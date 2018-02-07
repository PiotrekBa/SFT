package pl.piotrbartoszak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piotrbartoszak.entity.Training;
import pl.piotrbartoszak.entity.User;
import pl.piotrbartoszak.model.TrainingForUser;
import pl.piotrbartoszak.model.TrainingToSave;
import pl.piotrbartoszak.model.TrainingToView;
import pl.piotrbartoszak.model.TrainingToViewForUser;
import pl.piotrbartoszak.repository.TrainingRepository;
import pl.piotrbartoszak.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    UserRepository userRepository;

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
    public void addTraining(@RequestBody TrainingToSave t) {
        Training training = TrainingToSave.fromTrainingToSave(t);
        trainingRepository.save(training);
    }

    @GetMapping("/user")
    public TrainingToViewForUser allUserTraining() {
        User user = userRepository.findOne(1L);
        LocalDate startDate = LocalDate.now();
        LocalDate finishDate = startDate.plusDays(7);
        List<Training> trainings = trainingRepository
                .findTrainingsAtTheWeek(startDate,finishDate);
        List<TrainingForUser> trainingForUsers = new ArrayList<>();
        for (Training t : trainings) {
            trainingForUsers.add(TrainingForUser.forUserfromTraining(t, user));
        }
        TrainingToViewForUser trainingToViewForUser = new TrainingToViewForUser();
        trainingToViewForUser.setStartDate(startDate);
        trainingToViewForUser.setFinishDate(finishDate);
        trainingToViewForUser.setTrainings(trainingForUsers);
        return trainingToViewForUser;
    }

    @PutMapping("/user/{id}")
    public void signToTraining(@PathVariable long id) {

        User user = userRepository.findOne(1L);
        Training training = trainingRepository.findOne(id);
        List<User> users = training.getUsers();
        User userToDel = null;
        for(User u : users) {
            if (u.getId() == user.getId()) {
                userToDel = u;
            }
        }
        if (userToDel == null) {
            users.add(user);
        } else {
            users.remove(userToDel);
        }
        training.setUsers(users);
        trainingRepository.save(training);
    }
}
