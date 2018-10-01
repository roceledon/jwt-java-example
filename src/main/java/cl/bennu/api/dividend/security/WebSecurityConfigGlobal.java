package cl.bennu.api.dividend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ResourceBundle;

@EnableWebSecurity
@Configuration
public class WebSecurityConfigGlobal extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationEntryPoint unauthorizedHandler;
    private UserDetailsService userDetailsService;
    private String exclusion;

    public WebSecurityConfigGlobal() {
        this.unauthorizedHandler = new JwtAuthenticationEntryPoint();
        this.userDetailsService = new WebSecurityConfiguration().userDetailsService();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

        String path = resourceBundle.getString("route.authentication.prefix").trim();
        path += resourceBundle.getString("route.authentication.path").trim();

        setExclusion(path);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    public JwtAuthenticationEntryPoint getUnauthorizedHandler() {
        return unauthorizedHandler;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        // we don't need CSRF because our token is invulnerable
        .csrf().disable()
        .exceptionHandling().authenticationEntryPoint(getUnauthorizedHandler()).and()
        // don't create session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        if(this.exclusion != null) {
            http.authorizeRequests().antMatchers(exclusion + "/**").permitAll();
        }

        http.authorizeRequests()
            // allow anonymous resource requests
//            .antMatchers(
//                    HttpMethod.GET,
//                    "/",
//                    "/*.html",
//                    "/favicon.ico",
//                    "/**/*.html",
//                    "/**/*.css",
//                    "/**/*.js"
//            ).permitAll()
            .antMatchers(HttpMethod.POST, "/login/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/login/verify").permitAll()
            .anyRequest().authenticated();

        // Custom JWT based security filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();
    }

    public void setExclusion(String exclusion) {
        this.exclusion = exclusion;
    }
}