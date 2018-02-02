package pl.piotrbartoszak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piotrbartoszak.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
