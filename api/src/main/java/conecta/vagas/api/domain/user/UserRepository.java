package conecta.vagas.api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);

    Optional<User> findByID(Long id);
    boolean existsByEmail(String email); //teste

    @Query(value = "SELECT u.* FROM users u INNER JOIN users_tags ut ON u.id = ut.user_id WHERE ut.tag_id IN :tags GROUP BY u.id HAVING COUNT(ut.id) >= :minimumTags", nativeQuery = true)
    List<User> findByTags(@Param("tags") Collection<Long> tags, Integer minimumTags);
}