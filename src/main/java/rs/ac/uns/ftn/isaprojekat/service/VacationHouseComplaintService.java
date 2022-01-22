package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseComplaint;

import java.util.Set;

public interface VacationHouseComplaintService extends CrudService<VacationHouseComplaint, Long> {

    Set<VacationHouseComplaint> getAllByVacationHouse(VacationHouse house);

    Set<VacationHouseComplaint> getAllByUser(User user);

    void deleteById(Long id);

}
