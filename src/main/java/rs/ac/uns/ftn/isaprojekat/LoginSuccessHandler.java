package rs.ac.uns.ftn.isaprojekat;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isaprojekat.model.LoggedInUserDetails;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        LoggedInUserDetails userDetails = (LoggedInUserDetails) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if(userDetails.getRole().equals("admin")){
            redirectURL="adminPage";
        }else if( userDetails.getRole().equals("user")){
            redirectURL="userHomePage";
        }else if(userDetails.getRole().equals("admin_new")){
            redirectURL = "change_pass";
        }


        response.sendRedirect(redirectURL);
    }
}
