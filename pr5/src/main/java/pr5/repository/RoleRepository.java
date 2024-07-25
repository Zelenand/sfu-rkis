package pr5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pr5.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
