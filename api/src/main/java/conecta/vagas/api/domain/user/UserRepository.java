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

    @Query(value = "WITH RECURSIVE tag_hierarchy AS (\n" +
            "  SELECT id, parent_id\n" +
            "  FROM tags\n" +
            "  WHERE id IN :tags\n" +
            "\n" +
            "  UNION ALL\n" +
            "\n" +
            "  SELECT t.id, t.parent_id\n" +
            "  FROM tag_hierarchy th\n" +
            "  JOIN tags t ON th.id = t.parent_id\n" +
            ")\n" +
            "SELECT u.*\n" +
            "FROM users u\n" +
            "JOIN users_tags ut ON u.id = ut.user_id\n" +
            "JOIN tag_hierarchy th ON ut.tag_id = th.id\n" +
            "GROUP BY u.id\n" +
            "HAVING COUNT(ut.id) >= :minimumTags", nativeQuery = true)
    List<User> findByTags(@Param("tags") Collection<Long> tags, @Param("minimumTags") Integer minimumTags);
}