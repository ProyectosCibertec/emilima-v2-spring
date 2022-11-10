package pe.com.emilima.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.emilima.dms.model.DocumentRequest;

import java.math.BigInteger;

@Repository
public interface DocumentRequestRepository extends CrudRepository<DocumentRequest, BigInteger> {
}
