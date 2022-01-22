package rs.ac.uns.ftn.isaprojekat.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoggedInUserDetails implements UserDetails {

    String ROLE_PREFIX = "ROLE_";

    private User user;

    public LoggedInUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //in my case one user can only have one role

        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX+user.getUserRole()));
        return list;
    }

    public String getRole(){
        if(user.getUserRole()==UserRole.ADMIN){
           // System.out.println("user je admin - iz UserDetailsIMPL");
            return "admin";
        }else if (user.getUserRole()==UserRole.USER){
           // System.out.println("user je user - iz UserDetailsIMPL");
            return "user";
        }else if (user.getUserRole()==UserRole.ADMIN_NEW) {
           // System.out.println("admin new");
            return "admin_new";
        } else {
            return "";
        }

    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
