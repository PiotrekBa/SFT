package pl.piotrbartoszak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.piotrbartoszak.entity.Training;
import pl.piotrbartoszak.entity.User;
import pl.piotrbartoszak.model.*;
import pl.piotrbartoszak.repository.TrainingRepository;
import pl.piotrbartoszak.repository.UserRepository;
import pl.piotrbartoszak.service.LoginService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public TrainingToView allWeekTrainings(@SessionAttribute("user") String u) {
        String[] userSession = LoginService.sessionUser(u);
        if (userSession[0].equals("admin")) {
            LocalDate startDate = LocalDate.now();
            LocalDate finishDate = startDate.plusDays(7);
            List<Training> training = trainingRepository.findTrainingsAtTheWeek(startDate, finishDate);
            return TrainingToView.fromTrainingList(training, startDate, finishDate);
        }
        return null;
    }

    @PostMapping("")
    public void addTraining(@RequestBody TrainingToSave t) {
        Training training = TrainingToSave.fromTrainingToSave(t);
        trainingRepository.save(training);
    }

    @GetMapping("/checkUser")
    public void checkUser(HttpServletResponse response,
                          @SessionAttribute("user") String user) throws IOException {
        String[] userSession = LoginService.sessionUser(user);
        if (userSession[0].equals("user")) {
            response.sendRedirect("/user");
        }
        response.sendRedirect("/home");
        return;
    }

    @GetMapping("/user")
    public TrainingToViewForUser allUserTraining(
            @SessionAttribute("user") String u) throws IOException {

        String[] userSession = LoginService.sessionUser(u);

        if (userSession[0].equals("user")) {
            Long id = Long.parseLong(userSession[1]);
            User user = userRepository.findOne(id);
            System.out.println(user);
            LocalDate startDate = LocalDate.now();
            LocalDate finishDate = startDate.plusDays(7);
            List<Training> trainings = trainingRepository
                    .findTrainingsAtTheWeek(startDate, finishDate);
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
        return null;
    }

    @PutMapping("/user/{id}")
    public String signToTraining(@PathVariable long id,
                                 @SessionAttribute("user") String u) {

        String[] userSession = LoginService.sessionUser(u);

        System.out.println(u);
        Long userId = Long.parseLong(userSession[1]);
        User user = userRepository.findOne(userId);
        Training training = trainingRepository.findOne(id);
        List<User> users = training.getUsers();
        User userToDel = null;
        for(User us : users) {
            if (us.getId() == user.getId()) {
                userToDel = us;
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
