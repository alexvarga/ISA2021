package rs.ac.uns.ftn.isaprojekat;

import javax.servlet.http.HttpServletRequest;

public class MyAppUrl {
    public static String getSiteUrl(HttpServletRequest request){
        String url = request.getRequestURL().toString();
        return url.replace(request.getServletPath(), "");
    }
}
