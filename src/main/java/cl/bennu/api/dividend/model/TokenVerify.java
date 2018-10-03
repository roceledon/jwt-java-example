package cl.bennu.api.dividend.model;

import java.io.Serializable;

public class TokenVerify implements Serializable{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
