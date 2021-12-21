package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.UserRepository;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class UserJpaService implements UserService {

    private final UserRepository userRepository;

    public UserJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAll() {
        Set<User> people = new HashSet<>();
        userRepository.findAll().forEach(people::add);
        return people;
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public User save(Long aLong, User object) {
        return userRepository.save(object);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
