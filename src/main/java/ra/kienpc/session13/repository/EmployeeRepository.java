package ra.kienpc.session13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.kienpc.session13.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
