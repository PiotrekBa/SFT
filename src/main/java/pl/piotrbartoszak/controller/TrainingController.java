package pl.piotrbartoszak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piotrbartoszak.entity.Training;
import pl.piotrbartoszak.entity.User;
import pl.piotrbartoszak.model.*;
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

    @Autowired
    PageParameter pageParameter;

    @GetMapping("/{id}")
    public Training trainingDetails(@PathVariable long id) {
        Training training = trainingRepository.findOne(id);
        return training;
    }

    @GetMapping("all/{page}")
    public TrainingToView allTrainings(@PathVariable int page) {
        int elementsOnPage = pageParameter.getElementsOnPage();
        long counter = trainingRepository.count();
        int amountOfPage = (int) (counter/elementsOnPage+1);
        List<Training> trainings = trainingRepository
                    .findTrainingsWithLimit(elementsOnPage*(page-1),elementsOnPage);
        TrainingToView ttv = TrainingToView.fromTrainingList(trainings, null, null);
        ttv.setPages(amountOfPage);
        ttv.setElementsOnPage(elementsOnPage);
        return ttv;
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
        User user = userRepository.findOne(2L);
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
    public String signToTraining(@PathVariable long id) {
        User user = userRepository.findOne(2L);
        Training training = trainingRepository.findOne(id);
        List<User> users = training.getUsers();
        User userToDel = null;
        for(User u : users) {
            if (u.getId() == user.getId()) {
                userToDel = u;
            }
        }
        if (userToDel != null) {
            users.remove(userToDel);
        } else if (users.size() < training.getCapacity()) {
            users.add(user);
        } else {
            return "No vacancies";
        }
        training.setUsers(users);
        trainingRepository.save(training);
        return "";
    }
}
