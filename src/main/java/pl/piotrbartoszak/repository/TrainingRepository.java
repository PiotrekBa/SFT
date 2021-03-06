package pl.piotrbartoszak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.piotrbartoszak.entity.Training;

import java.time.LocalDate;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query("select t from Training t where t.date between ?1 AND ?2 order by t.date, t.time asc")
    List<Training> findTrainingsAtTheWeek(LocalDate startDate, LocalDate finishDate);

    @Query(value = "SELECT * FROM trainings t ORDER BY t.date DESC, t.time DESC LIMIT ?1, ?2", nativeQuery = true)
    List<Training> findTrainingsWithLimit(int offset, int count);
}
