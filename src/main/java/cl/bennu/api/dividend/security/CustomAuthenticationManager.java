package cl.bennu.api.dividend.security;

import cl.bennu.api.dividend.model.Authority;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Bennu on 05-12-2016.
 */
public class CustomAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        String sysUsername = resourceBundle.getString("user.username").trim();
        String sysPassword = resourceBundle.getString("user.password").trim();

        if(!sysUsername.equals(username)){
            throw new BadCredentialsException("1000");
        }

        //TODO: quitar estas 2 lineas si la contraseña se guarda encriptada
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        sysPassword = encoder.encode(sysPassword);

        //if (!encoder.matches(password, user.getPassword())) {
        if (!encoder.matches(password,sysPassword)) {
            throw new BadCredentialsException("1000");
        }

        //List<Authority> authority = AuthorityBusiness.getInstance().findByUserId(user.getId());
        Authority authority = new Authority();
        authority.setId(1L);
        authority.setName("user");

        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(authority);

        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        for(Authority auth : authorityList){
            grantedAuths.add(new SimpleGrantedAuthority(auth.getName()));
        }

        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
    }
}
