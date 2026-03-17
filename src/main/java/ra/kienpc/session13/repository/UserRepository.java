package ra.kienpc.session13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.kienpc.session13.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //UserRepository có method Optional<User> findByUsername(String username).
    Optional<User> findByUsername(String username);
}
