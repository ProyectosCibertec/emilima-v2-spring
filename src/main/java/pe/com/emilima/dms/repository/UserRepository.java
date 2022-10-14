package pe.com.emilima.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.emilima.dms.model.RequestState;
import pe.com.emilima.dms.model.User;

import java.math.BigInteger;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
