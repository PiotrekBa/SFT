package pl.piotrbartoszak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piotrbartoszak.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);
}
