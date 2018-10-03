package cl.bennu.api.dividend.model;

import java.io.Serializable;
import java.util.Date;

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private String token;
    private Date expiration;

    public JwtAuthenticationResponse() {
        super();
    }

    public JwtAuthenticationResponse(String token, Date expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}