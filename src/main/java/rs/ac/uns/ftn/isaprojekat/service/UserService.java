package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public interface UserService extends CrudService<User, Long> {

    User findByEmail(String email);

    User findByVerificationCode(String verificationCode);

    void sendVerificationEmail(User user, String url) throws UnsupportedEncodingException, MessagingException;

    boolean verifyUser(String verificationCode);


}
