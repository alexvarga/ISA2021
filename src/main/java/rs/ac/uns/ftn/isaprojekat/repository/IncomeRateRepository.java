package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.IncomeRate;

@Repository
public interface IncomeRateRepository extends CrudRepository<IncomeRate, Long> {

    Float findByEntityName(String name);

}
