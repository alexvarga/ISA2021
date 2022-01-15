package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.IncomeRate;

public interface IncomeRateService extends CrudService<IncomeRate, Long> {

    IncomeRate findByEntityName(String name);

}
