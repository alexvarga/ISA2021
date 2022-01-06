package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.UserRepository;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class UserJpaService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

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

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User findByVerificationCode(String verificationCode) {
        return userRepository.findByVerificationCode(verificationCode);
    }

    @Override
    public void sendVerificationEmail(User user, String siteUrl) throws UnsupportedEncodingException, MessagingException {
        String subject = "Verify registration";
        String sender = "isa-projekat";
        String content = "<p>Hello "+user.getFirstName() + ",</p>";
        content+="<p>Please click the link below to verify your registration. </p>";
        String verifyUrl = siteUrl+"/register/verify?code="+user.getVerificationCode(); //todo

        content+="<a href=\""+verifyUrl+"\">VERIFY</a>";

        content+= "<p>Thank you.</p>";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("isa.projekat.ftn.ra175.2012@gmail.com", sender);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);


    }



    @Override
    public boolean verifyUser(String verificationCode) {
        User user = findByVerificationCode(verificationCode);

        if(user==null || user.getEnabled()){
            return false;
        }else{
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }

    }

    @Override
    public void sendReservationConfirmationEmail(String entityName, String entityType, String dateFrom, String dateEnd, String address, String userMail) throws UnsupportedEncodingException, MessagingException {
        String subject = "Potvrda rezervacije";
        String sender = "isa-projekat";
        String content = "Uspešno ste rezervisali "+entityType+" "+entityName+ "u periodu od "+dateFrom+" do "+dateEnd+" sa adresom "+address+".";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("isa.projekat.ftn.ra175.2012@gmail.com", sender);
        helper.setTo(userMail);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
