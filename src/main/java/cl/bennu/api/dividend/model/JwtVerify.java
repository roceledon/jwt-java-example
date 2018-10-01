package cl.bennu.api.dividend.model;

import java.io.Serializable;

public class JwtVerify implements Serializable {
    Boolean verify;

    public Boolean getVerify() {
        return verify;
    }

    public void setVerify(Boolean verify) {
        this.verify = verify;
    }
}
