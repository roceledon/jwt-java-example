package cl.bennu.api.dividend.security;

import cl.bennu.api.dividend.model.JwtUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Bennu on 03-12-2016.
 */
@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return username -> {
            String sysUsername = resourceBundle.getString("user.username").trim();
            String sysPassword = resourceBundle.getString("user.password").trim();
            //User user = UserBusiness.getInstance().findByEmail(email);

            //TODO: quitar estas 2 lineas si la contraseña se guarda encriptada
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            sysPassword = encoder.encode(sysPassword);

            //Date lastPasswordResetDate = new Date(20180630);

            //if(user != null) {
            if(sysUsername.equals(username)){
                //return new JwtUser(user.getId(), user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("USER"), user.getEnabled(), user.getLastPasswordResetDate());
                return new JwtUser(1L, sysUsername, sysPassword, AuthorityUtils.createAuthorityList("USER"), true, null);
            } else {
                throw new UsernameNotFoundException("could not find the user whit username'" + username + "'");
            }
        };
    }
}
