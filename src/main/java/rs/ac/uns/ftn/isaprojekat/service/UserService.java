package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;


public interface UserService extends CrudService<User, Long> {

    User findByEmail(String email);
    boolean existsByEmail(String email);

    User findByVerificationCode(String verificationCode);


    void sendVerificationEmail(User user, String url) throws UnsupportedEncodingException, MessagingException;

    boolean verifyUser(String verificationCode);

    void sendReservationConfirmationEmail(String entity, String entityType, String dateFrom, String dateEnd,
                                          String address, String userMail) throws UnsupportedEncodingException, MessagingException;


    void sendReviewInfoEmail(String entityName, String entityType, String reviewContent, Float rating,
                             String firstName, String lastName, String ownerMail)
            throws UnsupportedEncodingException, MessagingException;
}
