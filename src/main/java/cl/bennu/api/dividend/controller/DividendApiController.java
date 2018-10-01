package cl.bennu.api.dividend.controller;

import cl.bennu.api.dividend.api.DividendApi;
import cl.bennu.api.dividend.model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class DividendApiController implements DividendApi{
    public ResponseEntity<Status> getStatus() {
        Status status = new Status();
        status.setId(200L);
        status.setMessage("success");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
