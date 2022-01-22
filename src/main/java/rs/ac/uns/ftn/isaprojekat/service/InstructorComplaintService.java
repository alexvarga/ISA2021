package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.model.InstructorComplaint;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

public interface InstructorComplaintService extends CrudService<InstructorComplaint, Long> {

    Set<InstructorComplaint> getAllByInstructor(Instructor instructor);

    Set<InstructorComplaint> getAllByUser(User user);

    void deleteById(Long id);
}
