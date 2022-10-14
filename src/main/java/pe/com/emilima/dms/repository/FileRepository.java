package pe.com.emilima.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.emilima.dms.model.File;

@Repository
public interface FileRepository extends CrudRepository<File, String> {
}
