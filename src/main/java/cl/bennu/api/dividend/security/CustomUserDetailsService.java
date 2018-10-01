package cl.bennu.api.dividend.security;

import cl.bennu.api.dividend.model.JwtUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {
    JwtUser loadUserByUsername(String var1) throws UsernameNotFoundException;
}
