package rs.ac.uns.ftn.isaprojekat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rs.ac.uns.ftn.isaprojekat.service.jpa.LoggedInUserDetailsService;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private LoginSuccessHandler loginSuccessHandler;

    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new LoggedInUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/profile", "/profile/").authenticated()
                .antMatchers("/houses/?").authenticated()
                .antMatchers("/login/").anonymous()
                .antMatchers("/register/").anonymous()
                .antMatchers("/adminPage").hasAuthority("ROLE_ADMIN")
                .antMatchers("/userHomePage").hasAuthority("ROLE_USER")
                .anyRequest()
                .permitAll()
                .and().formLogin()
                .usernameParameter("email").successHandler(loginSuccessHandler)
                .permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll();
    }
}
