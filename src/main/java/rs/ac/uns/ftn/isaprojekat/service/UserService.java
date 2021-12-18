package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.User;

@Service
public interface UserService extends CrudService<User, Long> {
}
