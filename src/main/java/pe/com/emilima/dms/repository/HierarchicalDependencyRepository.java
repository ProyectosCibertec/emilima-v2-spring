package pe.com.emilima.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.emilima.dms.model.HierarchicalDependency;

import java.math.BigInteger;

@Repository
public interface HierarchicalDependencyRepository extends CrudRepository<HierarchicalDependency, BigInteger> {
}
