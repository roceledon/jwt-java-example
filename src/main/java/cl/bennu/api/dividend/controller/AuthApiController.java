package cl.bennu.api.dividend.controller;

import cl.bennu.api.dividend.api.AuthApi;
import cl.bennu.api.dividend.model.JwtAuthenticationRequest;
import cl.bennu.api.dividend.model.JwtAuthenticationResponse;
import cl.bennu.api.dividend.model.JwtUser;
import cl.bennu.api.dividend.model.JwtVerify;
import cl.bennu.api.dividend.security.CustomAuthenticationManager;
import cl.bennu.api.dividend.security.CustomUserDetailsService;
import cl.bennu.api.dividend.security.JwtTokenUtil;
import cl.bennu.api.dividend.security.WebSecurityConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class AuthApiController implements AuthApi {
    @Value("${jwt.header}")
    private String tokenHeader;
    private CustomAuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private CustomUserDetailsService userDetailsService;

    public AuthApiController() {
        this.jwtTokenUtil = new JwtTokenUtil();
        this.userDetailsService = new WebSecurityConfiguration().userDetailsService();
        this.authenticationManager = new CustomAuthenticationManager();
    }

    @Override
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest request) throws AuthenticationException {

        try {
            // Perform the security
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Reload password post-security so we can generate token
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            DeviceType deviceType;

            if (request.getHeader("User-Agent").contains("Mobile")) {
                deviceType = DeviceType.MOBILE;
            } else {
                deviceType = DeviceType.NORMAL;
            }

            final String token = jwtTokenUtil.generateToken(userDetails, deviceType);

            // Return the token
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        //String username = jwtTokenUtil.getUsernameFromToken(token);
        //JwtUser user = userDetailsService.loadUserByUsername(username);

        //if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    public ResponseEntity<?> verifyAuthenticationToken(@RequestBody JwtAuthenticationResponse jwtAuthenticationResponse) {
        JwtVerify jwtVerify = new JwtVerify();
        try{
            if(jwtAuthenticationResponse != null) {
                String token = jwtAuthenticationResponse.getToken();
                String username = jwtTokenUtil.getUsernameFromToken(token);
                JwtUser user = userDetailsService.loadUserByUsername(username);

                Boolean verify = jwtTokenUtil.validateToken(token, user);

                if (verify != null) {

                    jwtVerify.setVerify(verify);

                    return ResponseEntity.ok(jwtVerify);
                }
            }
        }catch (Exception e){
            jwtVerify.setVerify(false);
            return new ResponseEntity<>(jwtVerify, HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.badRequest().body(null);
    }
}