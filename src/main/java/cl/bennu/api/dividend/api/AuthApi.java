package cl.bennu.api.dividend.api;

import cl.bennu.api.dividend.model.JwtAuthenticationRequest;
import cl.bennu.api.dividend.model.JwtAuthenticationResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "${route.authentication.prefix}")
public interface AuthApi {
    @RequestMapping(value = "${route.authentication.path}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest request) throws AuthenticationException;

    @RequestMapping(value = "${route.authentication.refresh}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request);

    @RequestMapping(value = "${route.authentication.verify}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    ResponseEntity<?> verifyAuthenticationToken(@RequestBody JwtAuthenticationResponse jwtAuthenticationResponse);
}