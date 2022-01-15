package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.IncomeRate;
import rs.ac.uns.ftn.isaprojekat.repository.IncomeRateRepository;
import rs.ac.uns.ftn.isaprojekat.service.IncomeRateService;

import java.util.Set;

@Profile("default")
@Service
public class IncomeRateJpaService implements IncomeRateService {

    private final IncomeRateRepository incomeRateRepository;

    public IncomeRateJpaService(IncomeRateRepository incomeRateRepository) {
        this.incomeRateRepository = incomeRateRepository;
    }

    @Override
    public IncomeRate findByEntityName(String name) {
        return incomeRateRepository.findByEntityName(name);
    }

    @Override
    public Set<IncomeRate> findAll() {
        return (Set<IncomeRate>) incomeRateRepository.findAll();
    }

    @Override
    public IncomeRate findById(Long aLong) {
        return incomeRateRepository.findById(aLong).orElse(null);
    }

    @Override
    public IncomeRate save(Long aLong, IncomeRate object) {
        return incomeRateRepository.save(object);
    }
}
