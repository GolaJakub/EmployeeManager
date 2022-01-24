package pl.pjatk.employeemanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pjatk.employeemanager.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("Select u FROM User u where u.username = :username")
    User getUserByUsername(@Param("username") String username);
}
