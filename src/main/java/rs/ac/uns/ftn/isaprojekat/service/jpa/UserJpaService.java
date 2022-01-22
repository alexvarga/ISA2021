package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.repository.UserRepository;
import rs.ac.uns.ftn.isaprojekat.service.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class UserJpaService implements UserService {

    private final UserRepository userRepository;

    private final BoatReservationService boatReservationService;
    private final VacationHouseReservationService vacationHouseReservationService;
    private final AdventureReservationService adventureReservationService;
    private final BoatReviewService boatReviewService;
    private final VacationHouseReviewService vacationHouseReviewService;
    private final AdventureReviewService adventureReviewService;
    private final InstructorSubscriptionService instructorSubscriptionService;
    private final BoatSubscriptionService boatSubscriptionService;
    private final VacationHouseSubscriptionService vacationHouseSubscriptionService;
    private final DeletionRequestService deletionRequestService;
    private final VacationHouseComplaintService vacationHouseComplaintService;
    private final BoatComplaintService boatComplaintService;
    private final InstructorComplaintService instructorComplaintService;



    @Autowired
    private JavaMailSender mailSender;

    public UserJpaService(UserRepository userRepository, BoatReservationService boatReservationService, VacationHouseReservationService vacationHouseReservationService, AdventureReservationService adventureReservationService, BoatReviewService boatReviewService, VacationHouseReviewService vacationHouseReviewService, AdventureReviewService adventureReviewService, InstructorSubscriptionService instructorSubscriptionService, BoatSubscriptionService boatSubscriptionService, VacationHouseSubscriptionService vacationHouseSubscriptionService, DeletionRequestService deletionRequestService, VacationHouseComplaintService vacationHouseComplaintService, BoatComplaintService boatComplaintService, InstructorComplaintService instructorComplaintService, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.boatReservationService = boatReservationService;
        this.vacationHouseReservationService = vacationHouseReservationService;
        this.adventureReservationService = adventureReservationService;
        this.boatReviewService = boatReviewService;
        this.vacationHouseReviewService = vacationHouseReviewService;
        this.adventureReviewService = adventureReviewService;
        this.instructorSubscriptionService = instructorSubscriptionService;
        this.boatSubscriptionService = boatSubscriptionService;
        this.vacationHouseSubscriptionService = vacationHouseSubscriptionService;
        this.deletionRequestService = deletionRequestService;
        this.vacationHouseComplaintService = vacationHouseComplaintService;
        this.boatComplaintService = boatComplaintService;
        this.instructorComplaintService = instructorComplaintService;
        this.mailSender = mailSender;
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
        String subject = "Potvrda registracije";
        String sender = "isa-projekat";
        String content = "<p>Pozdrav, "+user.getFirstName() + ",</p>";
        content+="<p>Molimo vas kliknite link da biste potvrdili registraciju. </p>";
        String verifyUrl = siteUrl+"/register/verify?code="+user.getVerificationCode(); //todo

        content+="<a href=\""+verifyUrl+"\">POTVRDA REGISTRACIJE</a>";

        content+= "<p>Hvala.</p>";


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
        String content = "Uspešno ste rezervisali "+entityType+" "+entityName+ " u periodu od "+dateFrom+" do "+dateEnd+" sa adresom "+address+".";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("isa.projekat.ftn.ra175.2012@gmail.com", sender);
        helper.setTo(userMail);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public void sendReviewInfoEmail(String entityName, String entityType, String reviewContent, Float rating,
                                    String firstName, String lastName, String ownerMail)
            throws UnsupportedEncodingException, MessagingException {
        String subject = "Nova ocena";
        String sender = "isa-projekat";
        String content = "<p>Vaš"+entityType+" "+entityName+ " ima novu reviziju od  korisnika "+firstName+ " "+lastName+".</p>";
        content+="<p>Tekst revizije: <i>"+reviewContent+"</i></p>";
        content+="<p>Ocena: "+rating+"</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("isa.projekat.ftn.ra175.2012@gmail.com", sender);
        helper.setTo(ownerMail);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }


    public void sendRequestDeniedEmail(String messageContent, String ownerMail)
            throws UnsupportedEncodingException, MessagingException {
        String subject = "Zahtev za brisanje naloga";
        String sender = "isa-projekat";
        String content = "<p>Vaš zahtev za brisanje naloga je odbijen.</p>";
        content+="<p>Tekst odgovora: <i>"+messageContent+"</i></p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("isa.projekat.ftn.ra175.2012@gmail.com", sender);
        helper.setTo(ownerMail);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    public void sendRequestAcceptedEmail(String messageContent, String ownerMail)
            throws UnsupportedEncodingException, MessagingException {
        String subject = "Zahtev za brisanje naloga";
        String sender = "isa-projekat";
        String content = "<p>Vaš zahtev za brisanje naloga je prihvaćen.</p>";
        content+="<p>Tekst odgovora: <i>"+messageContent+"</i></p>";
        content+="<p>Vaš nalog je obrisan.</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("isa.projekat.ftn.ra175.2012@gmail.com", sender);
        helper.setTo(ownerMail);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public void delete(User user) {
        Set<VacationHouseReservation> vhres = vacationHouseReservationService.getAllByUser(user);
        for(VacationHouseReservation vhr:vhres){
            vhr.setUser(null);
            vacationHouseReservationService.save(1L, vhr);
        }

        Set<AdventureReservation> ares = adventureReservationService.getAllByUser(user);
        for(AdventureReservation ar:ares){
            ar.setUser(null);
            adventureReservationService.save(1L, ar);
        }
        Set<BoatReservation> bres = boatReservationService.getAllByUser(user);
        for(BoatReservation br:bres){

            br.setUser(null);
            boatReservationService.save(1L, br);
        }

        Set<VacationHouseReview> vhrev = vacationHouseReviewService.getAllByUser(user);
        for (VacationHouseReview v:vhrev){
            if (v.getReviewStatus()==ReviewStatus.PENDING){
                vacationHouseReviewService.deleteById(v.getId());
            }else {
                v.setUser(null);
                vacationHouseReviewService.save(1L, v);
            }
        }
        Set<AdventureReview> arev = adventureReviewService.getAllByUser(user);
        for (AdventureReview a:arev){

            if(a.getReviewStatus()==ReviewStatus.PENDING){
                adventureReviewService.deleteById(a.getId());
            }else{
                a.setUser(null);
                adventureReviewService.save(1L, a);
            }

        }

        Set<BoatReview> brev = boatReviewService.getAllByUser(user);
        for(BoatReview b:brev){
            if(b.getReviewStatus()==ReviewStatus.PENDING){
                boatReviewService.deleteById(b.getId());
            }else {
                b.setUser(null);
                boatReviewService.save(1L, b);
            }
        }

        Set<VacationHouseSubscription> vsub = vacationHouseSubscriptionService.findAllByUser(user);
        for(VacationHouseSubscription v:vsub){
            vacationHouseSubscriptionService.deleteById(v.getId());
        }
        Set<BoatSubscription> bsub = boatSubscriptionService.findAllByUser(user);
        for(BoatSubscription bs:bsub){
            boatSubscriptionService.deleteById(bs.getId());
        }
        Set<InstructorSubscription> isub = instructorSubscriptionService.findAllByUser(user);
        for(InstructorSubscription i:isub){
            instructorSubscriptionService.deleteById(i.getId());
        }

        Set<DeletionRequest> dr = deletionRequestService.findAllByUser(user);
        for (DeletionRequest d:dr){
            deletionRequestService.deleteById(d.getId());
        }

        Set<BoatComplaint> comp = boatComplaintService.getAllByUser(user);
        for(BoatComplaint bc:comp){
            boatComplaintService.deleteById(bc.getId());
        }

        Set<VacationHouseComplaint> vcomp = vacationHouseComplaintService.getAllByUser(user);
        for(VacationHouseComplaint vc:vcomp){
            vacationHouseComplaintService.deleteById(vc.getId());
        }

        Set<InstructorComplaint> icomp = instructorComplaintService.getAllByUser(user);
        for (InstructorComplaint i:icomp){
            instructorComplaintService.deleteById(i.getId());
        }



        userRepository.delete(user);

    }

    public void sendComplaintResponseMail(String messageContent, String complaintContent, String ownerMail, String userMail, String entity, String entityName)
            throws UnsupportedEncodingException, MessagingException {
        String subject = "Žalba";
        String sender = "isa-projekat";
        String contentUser = "<p>Odgovor na vašu žalbu.</p>";
        contentUser+="<p>Tekst žalbe: <i>"+complaintContent+"</i></p>";

        contentUser+="<p>Tekst odgovora: <i>"+messageContent+"</i></p>";

        String contentOwner = "<p>Odgovor na žalbu za vaš"+entity+" "+entityName+".</p>";
        contentOwner+="<p>Tekst žalbe: <i>"+complaintContent+"</i></p>";
        contentOwner+="<p>Tekst odgovora: <i>"+messageContent+"</i></p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessage message2 = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("isa.projekat.ftn.ra175.2012@gmail.com", sender);


        helper.setSubject(subject);
        helper.setTo(ownerMail);
        helper.setText(contentOwner, true);
        mailSender.send(message);
        helper.setTo(userMail);
        helper.setText(contentUser, true);
        mailSender.send(message);
    }
}
