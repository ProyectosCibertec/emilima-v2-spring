package pe.com.emilima.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.emilima.dms.model.DocumentalSerie;

@Repository
public interface DocumentalSerieRepository extends CrudRepository<DocumentalSerie, String> {
}
