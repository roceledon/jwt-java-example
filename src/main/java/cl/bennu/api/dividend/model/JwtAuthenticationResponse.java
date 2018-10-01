package cl.bennu.api.dividend.model;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private String token;

    public JwtAuthenticationResponse() {
        super();
    }

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}